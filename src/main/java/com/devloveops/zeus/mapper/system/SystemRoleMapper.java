package com.devloveops.zeus.mapper.system;

import com.devloveops.zeus.domain.system.SystemRole;
import com.devloveops.zeus.domain.system.SystemRoleExample;
import java.util.List;

import com.devloveops.zeus.support.query.QuerySystemRole;
import org.apache.ibatis.annotations.Param;

public interface SystemRoleMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table system_role
     *
     * @mbg.generated
     */
    long countByExample(SystemRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table system_role
     *
     * @mbg.generated
     */
    int deleteByRoleId(SystemRole systemRole);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table system_role
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table system_role
     *
     * @mbg.generated
     */
    int insert(SystemRole record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table system_role
     *
     * @mbg.generated
     */
    int insertSelective(SystemRole record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table system_role
     *
     * @mbg.generated
     */
    List<SystemRole> selectByExampleWithBLOBs(SystemRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table system_role
     *
     * @mbg.generated
     */
    SystemRole selectByRoleId(SystemRole systemRole);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table system_role
     *
     * @mbg.generated
     */
    SystemRole selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table system_role
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") SystemRole record, @Param("example") SystemRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table system_role
     *
     * @mbg.generated
     */
    int updateByExampleWithBLOBs(@Param("record") SystemRole record, @Param("example") SystemRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table system_role
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") SystemRole record, @Param("example") SystemRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table system_role
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(SystemRole record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table system_role
     *
     * @mbg.generated
     */
    int updateByPrimaryKeyWithBLOBs(SystemRole record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table system_role
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(SystemRole record);

    List<SystemRole> selectByQueryCondition(QuerySystemRole querySystemRole);
    List<String> selectByUserId(@Param("user_id") String userId);
}