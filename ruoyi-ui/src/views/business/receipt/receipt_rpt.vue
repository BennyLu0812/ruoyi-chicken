<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" size="small" v-show="showSearch" label-width="100px">
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
      <el-form-item label="发货日期" prop="deliveryDates">
        <el-date-picker type="daterange" v-model="queryParams.deliveryDates" format="yyyy-MM-dd"
          value-format="yyyy-MM-dd" :style="{width: '100%'}" start-placeholder="开始日期" end-placeholder="结束日期"
          range-separator="至" clearable></el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['business:receiptDetails:export']"
        >导出明细</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading"  :data="customerReceiptList"
      :summary-method="getSummariesAmount" show-summary >

      <el-table-column label="序号" align="center"  >
        <template slot-scope="scope">
          <span>{{scope.$index + 1}}</span>
        </template>
      </el-table-column>
      <el-table-column label="客户" prop="customerName"  />
      <el-table-column label="金额" prop="totalAmount"  >
          <template slot-scope="scope">
               <span>{{scope.row.totalAmount}}元</span>
          </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width" >
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-view"
            @click="handleDetail(scope.row.customerId)"
            >详情
          </el-button>
        </template>
      </el-table-column>

    </el-table>
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"/>


    <!-- 详情 -->
    <el-dialog :title="title" :visible.sync="showDetails" width="90%" append-to-body>
        <el-table  :data="customerReceiptDetailList" >
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
          <el-table-column label="金额" prop="amount" width="150" >
              <template slot-scope="scope">
                   <span>{{scope.row.amount}}元</span>
              </template>
          </el-table-column>
          <el-table-column label="状态" align="center" prop="status" >
              <template slot-scope="scope">
                <dict-tag :options="dict.type.sys_normal_disable" :value="scope.row.status"/>
              </template>
          </el-table-column>
        </el-table>
    </el-dialog>

  </div>
</template>

<script>
import { customerReceiptList, customerReceiptDetailList } from "@/api/business/receipt_rpt";
import { optionselect as getCustomerOptionselect } from "@/api/business/customer";
import { isEmpty } from '@/utils/ruoyi';

export default {
  name: "customerReceipt",
  dicts: ['sys_normal_disable'],
  components: {},
  props: [],
  data() {
    return {
      // 遮罩层
      loading: false,
      // 显示搜索条件
      showSearch: true,
      // 显示詳情
      showDetails: false,
      // 总条数
      total: 0,
      // 詳情標題
      title: '',
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
      // dan
      customerReceiptDetailList:[],
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
    getSummariesAmount(param) {
          const { columns, data } = param;
          const sums = [];
          var totalAmount;
          columns.forEach((column, index) => {
            if (index === 0) {
              sums[index] = '合计';
              return;
            } else if (index === 2) {
              const values = data.map(item => Number(item[column.property]));
              if (!values.every(value => isNaN(value))) {
                 const values = data.map(item => Number(item[column.property]))
                 if (!values.every(value => isNaN(value))) {
                   sums[index] = values.reduce((prev, curr) => {
                      const value = Number(curr)
                      if (!isNaN(value)) {
                        return prev + curr
                      } else {
                        return prev
                      }
                   }, 0).toFixed(2)
                  }
                  sums[index] += ' 元';
              } else {
                sums[index] = 'N/A';
              }
            }
          });
          return sums;
        },

    /** 详情按钮操作 */
    handleDetail(customerId) {
      var  queryParamsDTO = {
        customerId: customerId,
        deliveryDateBeginTime: '',
        deliveryDateEndTime: ''
      };
      if (!isEmpty(this.queryParams.deliveryDates) ) {
          queryParamsDTO.deliveryDateBeginTime=this.queryParams.deliveryDates[0];
          queryParamsDTO.deliveryDateEndTime=this.queryParams.deliveryDates[1];
      }
      customerReceiptDetailList(queryParamsDTO).then(response => {
        console.log(response.rows);
        this.customerReceiptDetailList = response.rows;
        this.showDetails = true;
        this.title = "報表详情";
      });
    },
    /** 搜索按钮操作 */
    handleQuery() {
      if (isEmpty(this.queryParams.deliveryDates)
          && isEmpty(this.queryParams.customerId)) {
        this.$modal.msgError("请先填送货日期或客户");
        return;
      }

      if (!isEmpty(this.queryParams.deliveryDates) ) {
          this.queryParams.deliveryDateBeginTime=this.queryParams.deliveryDates[0];
          this.queryParams.deliveryDateEndTime=this.queryParams.deliveryDates[1];
      }
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.queryParams.deliveryDateBeginTime='';
      this.queryParams.deliveryDateEndTime='';
      this.handleQuery();
    },
    /** 查询单据头列表 */
    getList() {
      this.loading = true;
      customerReceiptList(this.queryParams).then(response => {
        this.customerReceiptList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
     /** 查询单据明細列表 */
    getDetailList() {
       customerReceiptDetailList(this.queryParams).then(response => {
          this.customerReceiptDetailList = response.rows;
       });
    },
     /** 导出按钮操作 */
     handleExport() {
       if (isEmpty(this.queryParams.deliveryDates)
           && isEmpty(this.queryParams.customerId)) {
         this.$modal.msgError("请先填送货日期或客户");
         return;
       }

       if (!isEmpty(this.queryParams.deliveryDates) ) {
           this.queryParams.deliveryDateBeginTime=this.queryParams.deliveryDates[0];
           this.queryParams.deliveryDateEndTime=this.queryParams.deliveryDates[1];
       }
       this.download('business/receipt/customerReceiptExport', {
         ...this.queryParams
       }, `receipt_${new Date().getTime()}.xlsx`)
     }

  }
}

</script>
<style>
</style>
