DROP DATABASE IF EXISTS remango_blog;
CREATE DATABASE remango_blog CHARSET = UTF8;
USE remango_blog;


# 日志表
CREATE TABLE log(
	id BIGINT AUTO_INCREMENT COMMENT '主键，自增',
	ip VARCHAR(32) NOT NULL COMMENT 'ip地址',
	operation VARCHAR(128) NOT NULL COMMENT '用户操作',
	method VARCHAR(64) NOT NULL COMMENT '处理方法名',
	params VARCHAR(2048) NOT NULL COMMENT '请求参数',
	spend_time INT NOT NULL COMMENT '处理时间，单位毫秒',
	create_time DATETIME NOT NULL COMMENT '记录时间',
	PRIMARY KEY (id)
);

# 留言板
CREATE TABLE message_board(
	id BIGINT AUTO_INCREMENT COMMENT '主键，自增',
	nickname VARCHAR(32) NOT NULL COMMENT '昵称',
	message VARCHAR(128) NOT NULL COMMENT '留言',
	create_time DATETIME NOT NULL COMMENT '记录时间',
	PRIMARY KEY (id)
);

CREATE TABLE article(
	id BIGINT AUTO_INCREMENT COMMENT '主键，自增',
	article_id BIGINT(32) NOT NULL COMMENT '文章id',
	author VARCHAR(128) NOT NULL COMMENT '作者',
	article_title VARCHAR(64) NOT NULL COMMENT '文章标题',
	type VARCHAR(2048) NOT NULL COMMENT '类别',
	summary TEXT NOT NULL COMMENT '摘要',
	categories VARCHAR(2048) NOT NULL COMMENT '分类',
	content TEXT NOT NULL COMMENT '文章内容',
	likes INT(20)  COMMENT '点赞数',
	last_article_id BIGINT(32)  COMMENT '上一篇文章id',
	next_article_id BIGINT(32)  COMMENT '写一篇文章id',
	timeline VARCHAR(64) NOT NULL COMMENT '时间轴',
	create_time DATETIME COMMENT '创建时间',
	update_time DATETIME  COMMENT '更新时间',
	visits INT(20)  COMMENT '访问数',
	PRIMARY KEY (id)
);


CREATE TABLE user(
	id BIGINT AUTO_INCREMENT COMMENT '主键，自增',
	username VARCHAR(32) NOT NULL COMMENT '用户名',
	password VARCHAR(128) NOT NULL COMMENT '密码',
	phone VARCHAR(32) NOT NULL COMMENT '电话号码',
	gender VARCHAR(10) NOT NULL COMMENT '性别',
	email VARCHAR(128) NOT NULL COMMENT '邮箱',
	avatar_url VARCHAR(2048) NOT NULL COMMENT '头像url',
	personal_profile DATETIME COMMENT '个人简介',
	create_time DATETIME COMMENT '创建时间',
	last_login_time DATETIME COMMENT '上次登录时间',
	PRIMARY KEY (id)
);

INSERT INTO user VALUES (1, 'root', '97da10d6a688e01e08944d2339eefb163fb5a9e066641c70f2f377f2173b36b8', '18207131787', 'male', '994857325@qq.com', 'https://remango-blog.oss-cn-shenzhen.aliyuncs.com/public/blogArticles/2019-03-26/1553599138.png', NULL, NULL ,NULL );

CREATE TABLE tags(
	id BIGINT AUTO_INCREMENT COMMENT '主键，自增',
	tags_name VARCHAR(32) NOT NULL COMMENT '文章标签',
	create_time DATETIME NOT NULL COMMENT '创建时间',
	PRIMARY KEY (id)
);

CREATE TABLE article_tags(
	id BIGINT AUTO_INCREMENT COMMENT '主键，自增',
	article_id BIGINT(32) NOT NULL COMMENT '文章id',
	tags_id BIGINT(32) NOT NULL COMMENT '标签id',
	PRIMARY KEY (id)
);

CREATE TABLE timeline(
	id BIGINT AUTO_INCREMENT COMMENT '主键，自增',
	timeline VARCHAR(32) NOT NULL COMMENT '时间轴',
	create_time DATETIME COMMENT '创建时间',
	PRIMARY KEY (id)
);

INSERT INTO timeline VALUES (1, '2019年3月', NULL );

CREATE TABLE role(
	id BIGINT AUTO_INCREMENT COMMENT '主键，自增',
	role VARCHAR(32) NOT NULL COMMENT '角色',
	PRIMARY KEY (id)
);

INSERT INTO role VALUES (1, 'super_admin');
INSERT INTO role VALUES (2, 'ordinary_admin');

CREATE TABLE user_role(
	id BIGINT AUTO_INCREMENT COMMENT '主键，自增',
	user_id BIGINT(32) NOT NULL COMMENT '文章id',
	role_id BIGINT(32) NOT NULL COMMENT '标签id',
	PRIMARY KEY (id)
);


# 创建用户并授权
GRANT ALL ON remango_blog.* TO 'remango_blog'@'%' IDENTIFIED BY '123456';