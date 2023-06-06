-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('单据', '2000', '1', 'receipt', 'business/receipt/index', 1, 0, 'C', '0', '0', 'business:receipt:list', '#', 'admin', sysdate(), '', null, '单据菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('单据查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'business:receipt:query',        '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('单据新增', @parentId, '2',  '#', '', 1, 0, 'F', '0', '0', 'business:receipt:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('单据修改', @parentId, '3',  '#', '', 1, 0, 'F', '0', '0', 'business:receipt:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('单据删除', @parentId, '4',  '#', '', 1, 0, 'F', '0', '0', 'business:receipt:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('单据导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'business:receipt:export',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('单据详情', @parentId, '6',  '#', '', 1, 0, 'F', '0', '0', 'business:receipt:detail',       '#', 'admin', sysdate(), '', null, '');