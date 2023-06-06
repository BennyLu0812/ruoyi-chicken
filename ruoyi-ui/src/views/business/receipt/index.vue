<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="送货日期" prop="deliveryDate">
        <el-date-picker clearable
          v-model="queryParams.deliveryDate"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择送货日期">
        </el-date-picker>
      </el-form-item>
      <!--<el-form-item label="总笼数" prop="totalCagesNumber">
        <el-input
          v-model="queryParams.totalCagesNumber"
          placeholder="请输入总笼数"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="总皮重" prop="totalTareWeight">
        <el-input
          v-model="queryParams.totalTareWeight"
          placeholder="请输入总皮重"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="总金额" prop="totalAmount">
        <el-input
          v-model="queryParams.totalAmount"
          placeholder="请输入总金额"
          clearable
          @keyup.enter.native="handleQuery"
        />-->
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['business:receipt:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['business:receipt:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['business:receipt:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['business:receipt:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="receiptList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="单据id" align="center" prop="receiptId" />
      <el-table-column label="送货日期" align="center" prop="deliveryDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.deliveryDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="总笼数" align="center" prop="totalCagesNumber" />
      <el-table-column label="总皮重" align="center" prop="totalTareWeight" />
      <el-table-column label="总金额" align="center" prop="totalAmount" >
          <template slot-scope="scope">
               <span>{{scope.row.totalAmount}}元</span>
          </template>
      </el-table-column>
      <el-table-column label="狀態" align="center" prop="status" >
          <template slot-scope="scope">
            <dict-tag :options="dict.type.sys_normal_disable" :value="scope.row.status"/>
          </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-view"
            @click="handleDetail(scope.row)"
            v-hasPermi="['business:receipt:detail']"
          >详情</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['business:receipt:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['business:receipt:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改单据头对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="70%" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" size="small" :inline="true" label-width="80px">
        <el-form-item label="送货日期" class="is-required" prop="deliveryDate">
          <el-date-picker clearable
            v-model="form.deliveryDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择送货日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="总笼数" class="is-required" prop="totalCagesNumber">
          <el-input v-model="form.totalCagesNumber" placeholder="请输入总笼数" />
        </el-form-item>
        <el-form-item label="总皮重" class="is-required" prop="totalTareWeight">
          <el-input v-model="form.totalTareWeight" placeholder="请输入总皮重" />
        </el-form-item>

        <el-divider content-position="center">单据明细信息</el-divider>
        <el-row :gutter="10" class="mb8">
          <el-col :span="1.5">
            <el-button type="primary" icon="el-icon-plus" size="mini" @click="handleAddReceiptDetails">添加</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button type="danger" icon="el-icon-delete" size="mini" @click="handleDeleteReceiptDetails">删除</el-button>
          </el-col>
        </el-row>
        <el-table :data="receiptDetailsList" :row-class-name="rowReceiptDetailsIndex"
              :summary-method="getSummariesAmount" show-summary
              @selection-change="handleReceiptDetailsSelectionChange" ref="receiptDetails">
          <el-table-column type="selection" width="50" align="center" />
          <el-table-column label="序号" align="center" prop="index" width="50"/>

          <el-table-column label="客户" prop="customerId" width="150">
            <template slot-scope="scope">
              <el-select v-model="scope.row.customerId" clearable filterable placeholder="请选择客户">
                  <el-option
                    v-for="customer in customerOptions"
                    :key="customer.customerId"
                    :label="customer.customerName"
                    :value="customer.customerId">
                    <span style="float: left">{{ customer.customerName }}</span>
                  </el-option>
              </el-select>
            </template>
          </el-table-column>

          <el-table-column label="品种" prop="goodsId" width="150">
            <template slot-scope="scope">
              <el-select v-model="scope.row.goodsId" clearable filterable placeholder="请选择品种">
                  <el-option
                    v-for="goods in goodsOptions"
                    :key="goods.goodsId"
                    :label="goods.goodsName"
                    :value="goods.goodsId">

                    <span style="float: left">{{ goods.goodsName }}</span>
                  </el-option>
              </el-select>
            </template>
          </el-table-column>

          <el-table-column label="毛重" prop="grossWeight" width="150">
            <template slot-scope="scope">
              <el-input v-model.trim="scope.row.grossWeight" placeholder="请输入毛重" />
            </template>
          </el-table-column>
          <el-table-column label="笼数" prop="cagesNumber" width="150">
            <template slot-scope="scope">
              <el-input v-model.trim="scope.row.cagesNumber" placeholder="请输入笼数" @blur="cagesNumberBlur(scope.row)" />
            </template>
          </el-table-column>
          <el-table-column label="单价" prop="price" width="150">
            <template slot-scope="scope">
              <el-input v-model.trim="scope.row.price" placeholder="请输入单价" @blur="priceBlur(scope.row)" />
            </template>
          </el-table-column>
          <el-table-column label="皮重" prop="tareWeight" width="150">
            <template slot-scope="scope">
              <el-input v-model.trim="scope.row.tareWeight"/>
            </template>
          </el-table-column>
          <el-table-column label="磅位" prop="poundPosition" width="150">
            <template slot-scope="scope">
              <el-input v-model.trim="scope.row.poundPosition" placeholder="请输入磅位" />
            </template>
          </el-table-column>
          <el-table-column label="金额" prop="amount" width="150">
            <template slot-scope="scope">
              <el-input v-model.trim="scope.row.amount"  />
            </template>
          </el-table-column>
          <el-table-column :visible="false" prop="status" width="150" />
        </el-table>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    <!-- 详情 -->
    <el-dialog :title="title" :visible.sync="openDetail" width="70%" append-to-body>
          <el-form ref="form" :model="form" :rules="rules" size="small" :inline="true" label-width="80px">
            <el-form-item  label="送货日期" class="is-required" prop="deliveryDate">
              <el-date-picker :disabled="true" clearable
                v-model="form.deliveryDate"
                type="date"
                value-format="yyyy-MM-dd"
                placeholder="请选择送货日期">
              </el-date-picker>
            </el-form-item>
            <el-form-item label="总笼数" class="is-required" prop="totalCagesNumber">
              <el-input :disabled="true" v-model="form.totalCagesNumber" placeholder="请输入总笼数" />
            </el-form-item>
            <el-form-item label="总皮重" class="is-required" prop="totalTareWeight">
              <el-input :disabled="true" v-model="form.totalTareWeight" placeholder="请输入总皮重" />
            </el-form-item>

            <el-divider content-position="center">单据明细信息</el-divider>

            <el-table :data="receiptDetailsList" :row-class-name="rowReceiptDetailsIndex"
                  :summary-method="getSummariesAmount" show-summary
                  @selection-change="handleReceiptDetailsSelectionChange" ref="receiptDetails">
              <el-table-column   width="50"  />
              <el-table-column label="序号" align="center" prop="index" width="50"/>
              <el-table-column label="客户" prop="customerId" width="150">
                <template slot-scope="scope">
                  <el-select :disabled="true" v-model="scope.row.customerId" clearable filterable placeholder="请选择客户">
                      <el-option
                        v-for="customer in customerOptions"
                        :key="customer.customerId"
                        :label="customer.customerName"
                        :value="customer.customerId">
                        <span style="float: left">{{ customer.customerName }}</span>
                      </el-option>
                  </el-select>
                </template>
              </el-table-column>
              <el-table-column label="品种" prop="goodsId" width="150">
                <template slot-scope="scope">
                  <el-select :disabled="true" v-model="scope.row.goodsId" clearable filterable placeholder="请选择品种">
                      <el-option
                        v-for="goods in goodsOptions"
                        :key="goods.goodsId"
                        :label="goods.goodsName"
                        :value="goods.goodsId">

                        <span style="float: left">{{ goods.goodsName }}</span>
                      </el-option>
                  </el-select>
                </template>
              </el-table-column>
              <el-table-column label="毛重" prop="grossWeight" width="150">
                <template slot-scope="scope">
                  <el-input :disabled="true" v-model.trim="scope.row.grossWeight" placeholder="请输入毛重" />
                </template>
              </el-table-column>
              <el-table-column label="笼数" prop="cagesNumber" width="150">
                <template slot-scope="scope">
                  <el-input :disabled="true" v-model.trim="scope.row.cagesNumber" placeholder="请输入笼数" @blur="cagesNumberBlur(scope.row)" />
                </template>
              </el-table-column>
              <el-table-column label="单价" prop="price" width="150">
                <template slot-scope="scope">
                  <el-input :disabled="true" v-model.trim="scope.row.price" placeholder="请输入单价" @blur="priceBlur(scope.row)" />
                </template>
              </el-table-column>
              <el-table-column label="皮重" prop="tareWeight" width="150">
                <template slot-scope="scope">
                  <el-input :disabled="true" v-model.trim="scope.row.tareWeight"/>
                </template>
              </el-table-column>
              <el-table-column label="磅位" prop="poundPosition" width="150">
                <template slot-scope="scope">
                  <el-input :disabled="true" v-model.trim="scope.row.poundPosition" />
                </template>
              </el-table-column>
              <el-table-column label="金额" prop="amount" width="150">
                <template slot-scope="scope">
                  <el-input :disabled="true" v-model.trim="scope.row.amount"/>
                </template>
              </el-table-column>
            </el-table>
            <el-table-column :visible="false" prop="status" width="150" />
          </el-form>
          <div slot="footer" class="dialog-footer">
            <el-button @click="closeDetail">返回</el-button>
          </div>
        </el-dialog>

  </div>
</template>

<script>
import { optionselect as getCustomerOptionselect } from "@/api/business/customer";
import { optionselect as getGoodsOptionselect } from "@/api/business/goods";
import { listReceipt, getReceipt, delReceipt, addReceipt, updateReceipt } from "@/api/business/receipt";
import { isEmpty } from '@/utils/ruoyi';

export default {
  name: "Receipt",
  dicts: ['sys_normal_disable'],
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 子表选中数据
      checkedReceiptDetails: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 单据头表格数据
      receiptList: [],
      // 单据明细表格数据
      receiptDetailsList: [],
      // 品种下拉
      goodsOptions: [],
      // 客户下拉
      customerOptions: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 是否详情
      openDetail: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        deliveryDate: null,
        totalCagesNumber: null,
        totalTareWeight: null,
        totalAmount: null,
        status: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      }
    };
  },
  created() {
    this.getList();

    /** 查询品种下拉列表 */
    getGoodsOptionselect().then(response => {
      this.goodsOptions = response.data;
    });

    /** 查询客户下拉列表 */
    getCustomerOptionselect().then(response => {
      this.customerOptions = response.data;
    });

  },
  watch: {
      // eslint-disable-next-line func-names
      'formData.value': function (val) {
        this.dataType = isNumberStr(val) ? 'number' : 'string'
      }
  },
  methods: {
    getSummariesAmount(param) {
      const { columns, data } = param;
      const sums = [];
      var totalAmount;
      columns.forEach((column, index) => {
        if (index === 1) {
          sums[index] = '合计';
          return;
        } else if (index === 9) {
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
              totalAmount = sums[index];
              sums[index] += ' 元';
          } else {
            sums[index] = 'N/A';
          }
        }
      });
      this.form.totalAmount = totalAmount;
      return sums;
    },
    /** 查询单据头列表 */
    getList() {
      this.loading = true;
      listReceipt(this.queryParams).then(response => {
        this.receiptList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 关闭详情
    closeDetail() {
      this.openDetail = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        receiptId: null,
        deliveryDate: null,
        totalCagesNumber: null,
        totalTareWeight: null,
        totalAmount: null,
        status: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null
      };
      this.receiptDetailsList = [];
      this.resetForm("form");
    },
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
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.receiptId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加单据";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const receiptId = row.receiptId || this.ids
      getReceipt(receiptId).then(response => {
        this.form = response.data;
        this.receiptDetailsList = response.data.receiptDetailsList;
        this.open = true;
        this.title = "修改单据";
      });
    },
    /** 详情按钮操作 */
    handleDetail(row) {
      this.reset();
      const receiptId = row.receiptId || this.ids
      getReceipt(receiptId).then(response => {
        this.form = response.data;
        this.receiptDetailsList = response.data.receiptDetailsList;
        this.openDetail = true;
        this.title = "单据详情";
      });
    },
    /*  笼数值输入完后，就计算皮重*/
    cagesNumberBlur(row) {
      /* 皮重=笼数 *（总皮重/总笼数）*/
      if (!isEmpty(this.form.totalTareWeight)) {
        var tareWeightValue = row.cagesNumber * (this.form.totalTareWeight/this.form.totalCagesNumber);
        row.tareWeight = tareWeightValue.toFixed(2);
      }
      if (!isEmpty(row.price)) {
        this.priceBlur(row);
      }
    },
    /*  单价输入完后，就计算金额*/
    priceBlur(row) {
      /* （毛重-皮重）*单价*/
      if (!isEmpty(row.price)) {
        var amountValue = (row.grossWeight - row.tareWeight) * row.price;
        row.amount = amountValue.toFixed(2);
      }
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          this.form.receiptDetailsList = this.receiptDetailsList;
          if (this.form.receiptId != null) {
            updateReceipt(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addReceipt(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const receiptIds = row.receiptId || this.ids;
      this.$modal.confirm('是否确认删除单据编号为"' + receiptIds + '"的数据项？').then(function() {
        return delReceipt(receiptIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
	/** 单据明细序号 */
    rowReceiptDetailsIndex({ row, rowIndex }) {
      row.index = rowIndex + 1;
    },
    /** 单据明细添加按钮操作 */
    handleAddReceiptDetails() {
      if (isEmpty(this.form.deliveryDate)
          || isEmpty(this.form.totalCagesNumber)
          || isEmpty(this.form.totalTareWeight)) {
          this.$modal.msgError("请先填好送货日期、总笼数、总皮重");
          return;
      }

      let obj = {};
      obj.goodsId = "";
      obj.customerId = "";
      obj.grossWeight = "";
      obj.cagesNumber = "";
      obj.price = "";
      obj.tareWeight = "";
      obj.poundPosition = "";
      obj.amount = "";
      obj.status = "0";
      this.receiptDetailsList.push(obj);
    },
    /** 单据明细删除按钮操作 */
    handleDeleteReceiptDetails() {
      if (this.checkedReceiptDetails.length == 0) {
        this.$modal.msgError("请先选择要删除的单据明细数据");
      } else {
        const receiptDetailsList = this.receiptDetailsList;
        const checkedReceiptDetails = this.checkedReceiptDetails;
        this.receiptDetailsList = receiptDetailsList.filter(function(item) {
          return checkedReceiptDetails.indexOf(item.index) == -1
        });
      }
    },
    /** 复选框选中数据 */
    handleReceiptDetailsSelectionChange(selection) {
      this.checkedReceiptDetails = selection.map(item => item.index)
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('business/receipt/export', {
        ...this.queryParams
      }, `receipt_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
