--- 用户表
create table if not exists users (
  id int unsigned PRIMARY KEY AUTO_INCREMENT,
  username varchar(64) UNIQUE NOT NULL,
  password varchar(128),
  realname varchar(64) COMMENT '真实姓名',
  create_time datetime COMMENT '创建时间',
  create_ip varchar(64) COMMENT '创建IP',
  passed boolean COMMENT '是否通过审核：true是，false否，null待审核',
  enabled boolean NOT NULL DEFAULT true COMMENT '是否启用'
) COMMENT='用户表';

--- 用户角色表
create table if not exists authorities (
  user_id int unsigned,
  authority varchar(36) COMMENT '角色名称',
  PRIMARY KEY (user_id, authority),
  FOREIGN KEY (user_id) REFERENCES users(id) ON UPDATE CASCADE ON DELETE CASCADE
) COMMENT='用户角色表';