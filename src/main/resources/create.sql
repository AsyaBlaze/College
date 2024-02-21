CREATE TABLE users_roles(
                            user_id int references users(id_user),
                            role_id int references roles(id_role),
                            PRIMARY KEY (user_id, role_id)
);

CREATE TABLE roles_rules(
                            role_id int references roles(id_role),
                            rule_id int references rules(id_rule),
                            PRIMARY KEY (role_id, rule_id)
);

CREATE TABLE users_items(
                            user_id int references users(id_user),
                            item_id int references items(id_item) UNIQUE,
                            PRIMARY KEY (user_id, item_id)
);

CREATE TABLE items_comments(
                               comment_id int references comments(id_comment),
                               item_id int references items(id_item),
                               PRIMARY KEY (comment_id, item_id)
);

CREATE TABLE items_attaches
(
                                item_id int references items(id_item),
                                attache_id int references attaches(id_attache),
                                PRIMARY KEY (item_id, attache_id)
);

CREATE TABLE items_categories(
                                 item_id int references items(id_item),
                                 categories_id int references categories(id_category),
                                 PRIMARY KEY (item_id, categories_id)
);

CREATE TABLE items_states(
                             item_id int references items(id_item),
                             state_id int references states(id_state),
                             PRIMARY KEY (item_id, state_id)
);