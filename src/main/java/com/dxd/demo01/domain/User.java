package com.dxd.demo01.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
//@Table(name = "user",uniqueConstraints = {@UniqueConstraint(columnNames={"id","email"})})
public class User implements Serializable {
    @Id
//    @Column(unique = true)
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    //账号
    private String account;
    //姓名
    private String username;
    //密码
    private String password;
    // 邮箱
//    @Column(unique = true)
    private String email;
}
