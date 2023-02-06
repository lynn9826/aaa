<template>
  <div>
    <el-table :data="tableData" border stripe style="width: 100%">
      <el-table-column prop="id" label="id" sortable  />
      <el-table-column prop="name" label="名字"  />
      <el-table-column prop="price" label="价格" />
      <el-table-column prop="author" label="作者" />
      <el-table-column prop="createTime" label="创作时间" />
      <el-table-column prop="cover" label="封面" />
      <el-table-column fixed="right" label="Operations" width="120">
        <template #default="scope">
          <el-button link type="primary" size="small" @click="handleEdit(scope.row)">编辑</el-button>
          <el-popconfirm title="Are you sure to delete this?" @confirm="handleDelete(scope.row.id)">
            <template #reference>
              <el-button link type="primary" size="small">删除</el-button>
            </template>
          </el-popconfirm>

        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
import request from "../utils/request";

export default {
  name: "Book",
  data(){
    return {
      form:{},
      dialogVisible: false,
      search:'',
      currentPage: 1,
      pageSize: 10,
      total:0,
      tableData: [

      ]
    }
  },
  created() {                  //加载
    this.load()
  },
  methods:{

    load(){
      request.get("http://2stzeh.natappfree.cc/book",{
        params: {
          pageNum: this.currentPage,
          pageSize: this.pageSize,
          search: this.search
        }

      }).then(res=>{
        console.log(res)
        this.tableData = res.data.records          //赋值
        this.total = res.data.total
      })
    },
  }
}

</script>

<style scoped>

</style>