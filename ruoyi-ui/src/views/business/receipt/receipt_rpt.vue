<template>
  <div class="app-container">
    <el-form ref="customerReceiptForm" :model="queryParams" :inline="true" size="small" label-width="100px">
      <el-form-item label="客户" prop="customerId">
        <el-select  v-model="queryParams.customerId" clearable filterable placeholder="请选择客户">
            <el-option
              v-for="customer in customerOptions"
              :key="customer.customerId"
              :label="customer.customerName"
              :value="customer.customerId">
              <span style="float: left">{{ customer.customerName }}</span>
            </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="发送日期" prop="deliveryDates">
        <el-date-picker type="daterange" v-model="queryParams.deliveryDates" format="yyyy-MM-dd"
          value-format="yyyy-MM-dd" :style="{width: '100%'}" start-placeholder="开始日期" end-placeholder="结束日期"
          range-separator="至" clearable></el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-table v-loading="loading" :data="customerReceiptList" >
      <el-table-column label="序号" align="center">
        <template slot-scope="scope">
          <span>{{scope.$index + 1}}</span>
        </template>
      </el-table-column>
      <el-table-column label="送货日期" align="center" prop="deliveryDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.deliveryDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="客户" prop="customerName" width="150" />
      <el-table-column label="品种" prop="goodsName" width="150" />
      <el-table-column label="毛重" prop="grossWeight" width="150" />
      <el-table-column label="笼数" prop="cagesNumber" width="150" />
      <el-table-column label="单价" prop="price" width="150" />
      <el-table-column label="皮重" prop="tareWeight" width="150" />
      <el-table-column label="磅位" prop="poundPosition" width="150" />
      <el-table-column label="金额" prop="amount" width="150" />
      <el-table-column label="狀態" align="center" prop="status" >
          <template slot-scope="scope">
            <dict-tag :options="dict.type.sys_normal_disable" :value="scope.row.status"/>
          </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
import { listCustomerReceipt } from "@/api/business/receipt_rpt";
import { optionselect as getCustomerOptionselect } from "@/api/business/customer";

export default {
  name: "customerReceipt",
  dicts: ['sys_normal_disable'],
  components: {},
  props: [],
  data() {
    return {
      // 遮罩层
      loading: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        customerId: '',
        deliveryDates: [],
        deliveryDateBeginTime: '',
        deliveryDateEndTime: ''
      },
      // 单据数据
      customerReceiptList: [],
      // 客户下来
      customerOptions: []
    }
  },
  computed: {},
  watch: {},
  created() {},
  mounted() {
    /** 查询客户下拉列表 */
    getCustomerOptionselect().then(response => {
      this.customerOptions = response.data;
    });
  },
  methods: {
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    /** 查询单据头列表 */
    getList() {
      this.loading = true;
      listCustomerReceipt(this.queryParams).then(response => {
        console.log(response.rows);
        this.customerReceiptList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    }

  }
}

</script>
<style>
</style>
