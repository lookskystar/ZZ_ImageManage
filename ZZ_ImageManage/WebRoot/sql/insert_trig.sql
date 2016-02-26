/**
  * 工序记录状态判断
  * 1、判断图片数量 视频时长 是否符合标准
  * 2、判断第一张图片与最后一张图片时间差 是否符合标准
  * 3、
**/
create or replace trigger insert_trig 
after insert
on take_detail_rec for each row
declare 
        PRAGMA AUTONOMOUS_TRANSACTION;
        
        v_pro_rec_id number; --工序记录ID
        
        v_image_count number;--作业图片数量
        v_video_count_time number;--作业视频总长度
             
        v_image_num number;--标准图片数量
        v_video_time number;--标准视频长度
        v_image_time_difference number;--标准第一张与最后一张的时间差
        --v_pro_time_difference number;--同道工序的工次时间差
        
        v_status number;--工序状态 0:合格 1:缺省 2:时间异常
        
        v_first varchar2(20);--第一张时间
        v_last varchar2(20);--最后一张时间
        v_image_difftime number;--实际作业第一与最后一张的时间差
        v_old_image_count number; --作业记录图片张数
begin
        v_pro_rec_id := :NEW.pro_id;
        
        --查找标准工序表中图片的数量
        select t1.image_num,t1.video_time,t1.image_time_difference
        into v_image_num,v_video_time,v_image_time_difference
        from procedure_info t1,procedure_inforec t2 
        where 
            t1.pro_id=t2.pro_id and t2.prorec_id=v_pro_rec_id;
        
        --图片数量 加入当前数量
        select count(t.rowid)+1 into v_image_count from take_detail_rec t where t.pro_id=v_pro_rec_id and t.type=0;
        --视频时长
        select sum(case when t.video_time is null then 0 else t.video_time end) into v_video_count_time from take_detail_rec t where t.pro_id=v_pro_rec_id and t.type=1;
        if v_video_count_time is null then
           v_video_count_time:=0;
        end if;
        v_video_count_time := v_video_count_time + :NEW.video_time;--加入当前时间
 
        if (v_image_count<v_image_num and v_video_count_time<v_video_time) then
           v_status := 1;
        else
           if v_video_count_time<v_video_time and v_image_count>0 then
              select count(*) into v_old_image_count from take_detail_rec t where t.pro_id=v_pro_rec_id and t.type=0;
              
              if v_old_image_count>0 then
               --第一张图片时间
               select take_time into v_first from (select t.take_time from take_detail_rec t where t.pro_id=v_pro_rec_id and t.type=0 order by t.take_time) tmp_table where rownum=1;
              end if; 
               --最后一张时间
               v_last := :NEW.take_time;
               
               if v_first is null or v_last is null then
                   v_status := 0;
               else
                  select round(to_number(TO_DATE(v_last,'yyyy-mm-dd hh24:mi:ss')-TO_DATE(v_first,'yyyy-mm-dd hh24:mi:ss'))*1440) into v_image_difftime from dual; 
                  if v_image_difftime < v_image_time_difference then
                     v_status := 2;
                  else
                     v_status := 0;
                  end if;
               end if;
           end if;
        end if;

        update procedure_inforec set status=v_status where prorec_id=v_pro_rec_id;--修改工序记录表
        commit;
end;