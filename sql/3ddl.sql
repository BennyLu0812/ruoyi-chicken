-- 数据库类型：utf8_general_ci
create table ye_customer (
  customer_id       bigint(20)      not null auto_increment comment '客户id',
  customer_phone    varchar(20) default '' comment '联系电话',
  customer_name     varchar(64) default '' comment '客户名称',
  customer_address  varchar(250) default '' comment '客户地址',
  status            char(1)         default '0' comment '状态',
  create_by         varchar(64)    default '' comment '创建人',
  create_time 	    date comment '创建时间',
  update_by         varchar(64)    default '' comment '修改人',
  update_time       date comment '修改时间',
  primary key (customer_id)
) engine=innodb auto_increment=1 comment = '客户表';

create table ye_goods (
  goods_id       bigint(20)      not null auto_increment comment '品种id',
  goods_name     varchar(64) default '' comment '品种名称',
  goods_remark  varchar(2000) default '' comment '备注',
  status            char(1)         default '0' comment '状态',
  create_by         varchar(64)    default '' comment '创建人',
  create_time 	    date comment '创建时间',
  update_by         varchar(64)    default '' comment '修改人',
  update_time       date comment '修改时间',
  primary key (goods_id)
) engine=innodb auto_increment=1 comment = '品种表';


create table ye_receipt (
  receipt_id        bigint(20)      not null auto_increment comment '单据id',
  delivery_date     date comment '送货日期',
  total_cages_number bigint(20) default 0 comment '总笼数',
  total_tare_weight double comment '总皮重',
  total_amount      double comment '总金额',
  status            char(1)         default '0' comment '状态',
  create_by         varchar(64)    default '' comment '创建人',
  create_time 	    date comment '创建时间',
  update_by         varchar(64)    default '' comment '修改人',
  update_time       date comment '修改时间',
  primary key (receipt_id)
)  engine=innodb auto_increment=1 comment = '单据头表';

create table ye_receipt_details (
  receipt_details_id bigint(20)      not null auto_increment comment '单据明细id',
  receipt_id        bigint(20)      not null comment '单据id',
  goods_id          bigint(20)      not null comment '品种',
  customer_id       bigint(20)      not null comment '客户id',
  gross_weight double  comment '毛重',
  cages_number bigint(10) default 0 comment '笼数',
  price double  comment '单价',
  tare_weight double comment '皮重',
  pound_position double comment '磅位',
  amount      double comment '金额',
  status            char(1)         default '0' comment '状态',
  create_by         varchar(64)    default '' comment '创建人',
  create_time 	    date comment '创建时间',
  update_by         varchar(64)    default '' comment '修改人',
  update_time       date comment '修改时间',
  primary key (receipt_details_id),
  foreign key (receipt_id) references ye_receipt(receipt_id),
  foreign key (goods_id) references ye_goods(goods_id),
  foreign key (customer_id) references ye_customer(customer_id)
) engine=innodb auto_increment=1 comment = '单据明细表';





