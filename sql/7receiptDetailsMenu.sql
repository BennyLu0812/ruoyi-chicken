-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('单据报表', '2000', '4', 'receipt_rpt', 'business/receipt/receipt_rpt', 1, 0, 'C', '0', '0', 'business:receiptDetails:list', '#', 'admin', sysdate(), '', null, '单据报表菜单');


-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('单据明细查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'business:receiptDetails:query',        '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('单据明细导出', @parentId, '2',  '#', '', 1, 0, 'F', '0', '0', 'business:receiptDetails:export',       '#', 'admin', sysdate(), '', null, '');