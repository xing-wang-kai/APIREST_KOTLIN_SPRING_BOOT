alter table if exists products
add constraint FKdb050tk37qryv15hd932626th
       foreign key (user_id)
       references users;
