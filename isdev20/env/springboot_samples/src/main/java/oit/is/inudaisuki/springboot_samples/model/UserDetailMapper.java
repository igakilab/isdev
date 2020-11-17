package oit.is.inudaisuki.springboot_samples.model;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserDetailMapper {

  @Select("SELECT USERID, NAME, JOBID FROM USERDETAIL WHERE USERID= #{userId}")
  UserDetail getUserDetaiById(String userId);

}
