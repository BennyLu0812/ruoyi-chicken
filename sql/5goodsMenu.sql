-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('品种', '2000', '2', 'goods', 'business/goods/index', 1, 0, 'C', '0', '0', 'business:goods:list', '#', 'admin', sysdate(), '', null, '品种菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('品种查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'business:goods:query',        '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('品种新增', @parentId, '2',  '#', '', 1, 0, 'F', '0', '0', 'business:goods:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('品种修改', @parentId, '3',  '#', '', 1, 0, 'F', '0', '0', 'business:goods:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('品种删除', @parentId, '4',  '#', '', 1, 0, 'F', '0', '0', 'business:goods:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('品种导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'business:goods:export',       '#', 'admin', sysdate(), '', null, '');