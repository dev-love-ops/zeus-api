package com.devloveops.zeus.domain.system;

import lombok.Data;

import java.util.List;

/**
 * @author RockyWu
 * @date 2019-07-31 22:36
 */
@Data
public class Permission{

    /**
     * 权限类型, 0: 页面菜单, 包括了菜单的显示与否和前端路由跳转的权限控制, 1: 表示后端接口权限和前端组件的权限, 比如有没有某个按钮的权限
     * 一般没有删除用户的后端接口权限的时候, 前端也不会显示删除按钮的, 不然用户体验是很差的 (前端+后盾)
     */
    private Integer type;

    /**
     * 后端权限的名称, 比如system:user:get(后端)
     */
    private String permissionId;

    /**
     * 前端路由的名称, 同时也是后端权限的名称(前端+后端)
     */
    private String name;

    /**
     * 菜单的标题, 就是vue路由中的title字段(前端)
     */
    private String title;

    /**
     * 页面路由的路径(前端)
     */
    private String path;

    /**
     * 路由引用的前端组件, 一般一级菜单会使用到(前端)
     */
    private String component;

    /**
     * 前端菜单图标(前端)
     */
    private String icon;

    /**
     * 菜单层级, 0: 表示一级菜单, 1: 表示二级菜单(前端)
     */
    private Integer level;

    /**
     * 父级ID, 一般用于菜单之间的关系, 权限和菜单之间的关系, 这个是符合实际情况的, 比如某个用户没有用户页面, 那么他也同时不会有该页面下所有按钮
     * 的权限, 如果把页面下面的按钮的权限存储为该页面的子权限的话,就很好控制了
     */
    private String parentId;

    /**
     * 备注
     */
    private String description;

    /**
     * 用于前端菜单的排序等(前端)
     */
    private Integer sortOrder;


    /**
     * 子菜单/子权限
     */
    private List<Permission> children;

}
