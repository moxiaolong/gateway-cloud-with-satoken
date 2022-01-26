CREATE TABLE `auth_permission`
(
    `id`          bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '' ID '',
    `is_deleted`  tinyint(1) NOT NULL DEFAULT '' 0 '' COMMENT '' 删除标记 '',
    `create_by`   bigint             DEFAULT ''0 '' COMMENT '' 创建人 '',
    `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '' 创建时间 '',
    `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '' 更新时间 '',
    `uri`         varchar(100)       DEFAULT NULL COMMENT '' 权限路径 '',
    `description` varchar(100)       DEFAULT NULL COMMENT '' 描述 '',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10000006 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT=''权限''

CREATE TABLE `auth_role`
(
    `id`          bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '' ID '',
    `is_deleted`  tinyint(1) NOT NULL DEFAULT '' 0 '' COMMENT '' 删除标记 '',
    `create_by`   bigint             DEFAULT ''0 '' COMMENT '' 创建人 '',
    `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '' 创建时间 '',
    `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '' 更新时间 '',
    `role_name`   varchar(100)       DEFAULT NULL COMMENT '' 角色名 '',
    `description` varchar(100)       DEFAULT NULL COMMENT '' 描述 '',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10000006 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT=''角色''

CREATE TABLE `auth_role_permission`
(
    `id`                 bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '' ID '',
    `is_deleted`         tinyint(1) NOT NULL DEFAULT '' 0 '' COMMENT '' 删除标记 '',
    `create_by`          bigint             DEFAULT ''0 '' COMMENT '' 创建人 '',
    `create_time`        timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '' 创建时间 '',
    `update_time`        timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '' 更新时间 '',
    `auth_role_id`       bigint             DEFAULT NULL COMMENT '' 角色 '',
    `auth_permission_id` bigint             DEFAULT NULL COMMENT '' 权限 '',
    PRIMARY KEY (`id`),
    KEY                  `idx_auth_role_permission_auth_role_id` (`auth_role_id`),
    KEY                  `idx_auth_role_permission_auth_permission_id` (`auth_permission_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10000006 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT=''角色权限中间表''

CREATE TABLE `auth_user`
(
    `id`          bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '' ID '',
    `is_deleted`  tinyint(1) NOT NULL DEFAULT '' 0 '' COMMENT '' 删除标记 '',
    `create_by`   bigint             DEFAULT ''0 '' COMMENT '' 创建人 '',
    `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '' 创建时间 '',
    `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '' 更新时间 '',
    `user_name`   varchar(100)       DEFAULT NULL COMMENT '' 用户名 '',
    `passwd`      varchar(100)       DEFAULT NULL COMMENT '' 密码 '',
    PRIMARY KEY (`id`),
    UNIQUE KEY `user_name_passwd_upk` (`user_name`,`passwd`)
) ENGINE=InnoDB AUTO_INCREMENT=10000005 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT=''账户''

CREATE TABLE `auth_user_role`
(
    `id`           bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '' ID '',
    `is_deleted`   tinyint(1) NOT NULL DEFAULT '' 0 '' COMMENT '' 删除标记 '',
    `create_by`    bigint             DEFAULT ''0 '' COMMENT '' 创建人 '',
    `create_time`  timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '' 创建时间 '',
    `update_time`  timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '' 更新时间 '',
    `auth_user_id` bigint             DEFAULT NULL COMMENT '' 用户id '',
    `auth_role_id` bigint             DEFAULT NULL COMMENT '' 角色id '',
    PRIMARY KEY (`id`),
    KEY            `idx_auth_user_role_auth_user_id` (`auth_user_id`),
    KEY            `idx_auth_user_role_auth_role_id` (`auth_role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10000006 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT=''用户角色中间表''

CREATE TABLE `dictionary`
(
    `id`           bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '' ID '',
    `parent_id`    bigint unsigned NOT NULL COMMENT '' 父ID '',
    `tenant_id`    bigint       NOT NULL DEFAULT ''0 '' COMMENT '' 租户ID '',
    `app_module`   varchar(50)           DEFAULT NULL COMMENT '' 应用模块 '',
    `type`         varchar(50)  NOT NULL COMMENT '' 字典类型 '',
    `item_name`    varchar(100) NOT NULL COMMENT '' 显示名 '',
    `item_value`   varchar(100)          DEFAULT NULL COMMENT '' 存储值 '',
    `description`  varchar(100)          DEFAULT NULL COMMENT '' 描述说明 '',
    `extdata`      varchar(200)          DEFAULT NULL COMMENT '' 扩展JSON '',
    `sort_id`      smallint     NOT NULL DEFAULT ''99 '' COMMENT '' 排序号 '',
    `is_editable`  tinyint(1) NOT NULL DEFAULT '' 1 '' COMMENT '' 是否可改 '',
    `is_deletable` tinyint(1) NOT NULL DEFAULT '' 1 '' COMMENT '' 是否可删 '',
    `is_deleted`   tinyint(1) NOT NULL DEFAULT '' 0 '' COMMENT '' 删除标记 '',
    `create_time`  timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '' 创建时间 '',
    PRIMARY KEY (`id`),
    KEY            `idx_directory` (`type`,`item_value`),
    KEY            `idx_directory_tenant` (`tenant_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10000 DEFAULT CHARSET=utf8mb3 COMMENT=''数据字典''

