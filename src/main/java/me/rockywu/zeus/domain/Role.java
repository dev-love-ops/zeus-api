package me.rockywu.zeus.domain;

import java.util.Set;

/**
 * @author RockyWu
 * @date 2019-01-20 10:27
 */
public class Role {
    private int id;
    private String name;
    private Set<User> users;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    /**
     * 在 @PreAuthorize 中我们可以利用内建的 SPEL 表达式：比如 'hasRole("ADMIN")' 来决定哪些用户有权访问。
     * 需注意的一点是 hasRole 表达式认为每个角色名字前都有一个前缀 'ROLE_'。所以这里的 'ADMIN' 其实在认证的时候返回的是 'ROLE_ADMIN'。
     * 这个 @PreAuthorize 可以修饰Controller也可修饰Controller中的方法
     * @return String
     */
    public String getRoleName() {
        return "ROLE_" + name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "Role{" +
                "name='" + name + '\'' +
                ", users=" + users +
                '}';
    }
}
