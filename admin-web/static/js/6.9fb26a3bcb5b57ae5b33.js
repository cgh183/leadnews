webpackJsonp([6],{YJNj:function(e,t,a){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var r=a("Xxa5"),n=a.n(r),s=a("Dd8w"),l=a.n(s),i=a("exGp"),o=a.n(i),u={render:function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("section",{staticClass:"filter"},[a("div",{staticClass:"filter-container"},[a("el-form",{ref:"form",attrs:{inline:!0}},[a("el-form-item",{attrs:{label:"账号/手机号："}},[a("el-input",{staticClass:"filter-item",attrs:{placeholder:"请输入账号或手机号",clearable:""},on:{change:e.queryData},model:{value:e.name,callback:function(t){e.name=t},expression:"name"}})],1),e._v(" "),a("el-form-item",{attrs:{label:"审核状态："}},[a("el-select",{attrs:{placeholder:"请选择状态",clearable:""},on:{change:e.changeStatus},model:{value:e.status,callback:function(t){e.status=t},expression:"status"}},e._l(e.statusList,function(e){return a("el-option",{key:e.value,attrs:{label:e.label,value:e.value}})}),1)],1)],1)],1)])},staticRenderFns:[]},c=a("VU/8")({props:["changeParam","addData"],data:function(){return{statusList:[{label:"全部",value:""},{label:"正常",value:0},{label:"冻结",value:1}],name:"",status:""}},methods:{queryData:function(){var e=[];this.name&&e.push({filed:"name",type:"like",value:this.name}),""!==this.status&&e.push({filed:"status",type:"eq",value:this.status}),this.changeParam(e)},changeStatus:function(){this.queryData()}}},u,!1,null,null,null).exports,f=a("xT6B"),m=a("t5DY"),d={props:["host","list","fileds","table","pageSize","total","changePage","changeStatus","editData","viewData"],data:function(){return{listPage:{currentPage:1},id:{filed:"id",type:"eq",value:""},setForStatus:{status:""}}},methods:{pageChange:function(e){this.changePage&&this.changePage({page:e})},dateFormat:function(e){return f.a.format13HH(e)},showFreezeConfirm:function(){var e=this;return o()(n.a.mark(function t(){return n.a.wrap(function(t){for(;;)switch(t.prev=t.next){case 0:return t.next=2,e.$confirm("冻结用户无法登陆进行操作，是否确认冻结该用户？","冻结确认",{confirmButtonText:"冻结",confirmButtonClass:"el-button--danger",cancelButtonText:"取消",cancelButtonClass:"el-button--warning"});case 2:return t.abrupt("return",t.sent);case 3:case"end":return t.stop()}},t,e)}))()},operateForConfirm:function(e,t,a){var r=this;return o()(n.a.mark(function s(){return n.a.wrap(function(n){for(;;)switch(n.prev=n.next){case 0:if(1!==t){n.next=13;break}return n.prev=1,n.next=4,r.showFreezeConfirm();case 4:return n.next=6,r.operateForDisable(e,t,a);case 6:n.next=11;break;case 8:n.prev=8,n.t0=n.catch(1),r.$message({type:"info",message:"已取消冻结"});case 11:n.next=15;break;case 13:return n.next=15,r.operateForDisable(e,t,a);case 15:case"end":return n.stop()}},s,r,[[1,8]])}))()},operateForDisable:function(e,t,a){var r=this;return o()(n.a.mark(function s(){var l,i;return n.a.wrap(function(n){for(;;)switch(n.prev=n.next){case 0:return r.id.value=e,l={name:r.table,where:[r.id],sets:[{filed:"status",value:t}]},n.next=4,Object(m.b)(l);case 4:200===(i=n.sent).code?(r.changeStatus(a,t),r.$message({type:"success",message:"操作成功！"})):r.$message({type:"error",message:i.errorMessage});case 6:case"end":return n.stop()}},s,r)}))()},operateForView:function(e){this.viewData(e)},open:function(e){var t=this;this.$prompt(e,"提示",{confirmButtonText:"确定",cancelButtonText:"取消"}).then(function(e){var a=e.value;t.params.msg=a,t.updateData(t.params)}).catch(function(){})}}},p={render:function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",[a("section",{staticClass:"result"},[a("el-table",{attrs:{data:e.list,"header-cell-style":{textAlign:"center",fontWeight:"600"},"cell-style":{textAlign:"center"},"highlight-current-row":""}},[a("el-table-column",{attrs:{label:"序号",type:"index",width:"50"}}),e._v(" "),a("el-table-column",{attrs:{label:"账号"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("span",[e._v(e._s(t.row.name))])]}}])}),e._v(" "),a("el-table-column",{attrs:{label:"姓名"},scopedSlots:e._u([{key:"default",fn:function(e){return[a("span")]}}])}),e._v(" "),a("el-table-column",{attrs:{label:"邮箱"},scopedSlots:e._u([{key:"default",fn:function(e){return[a("span")]}}])}),e._v(" "),a("el-table-column",{attrs:{label:"手机"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("span",[e._v(e._s(t.row.phone))])]}}])}),e._v(" "),a("el-table-column",{attrs:{label:"最后活动时间"},scopedSlots:e._u([{key:"default",fn:function(e){return[a("span")]}}])}),e._v(" "),a("el-table-column",{attrs:{label:"实名认证"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("span",[e._v(e._s(t.row.is_certification?"是":"否"))])]}}])}),e._v(" "),a("el-table-column",{attrs:{label:"身份认证"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("span",[e._v(e._s(t.row.is_identity_authentication?"是":"否"))])]}}])}),e._v(" "),a("el-table-column",{attrs:{label:"账号状态"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("span",[e._v(e._s(t.row.status?"冻结":"正常"))])]}}])}),e._v(" "),a("el-table-column",{attrs:{label:"操作"},scopedSlots:e._u([{key:"default",fn:function(t){return[1==t.row.status?a("el-button",{staticClass:"el-button--primary-text",attrs:{type:"text"},on:{click:function(a){return e.operateForConfirm(t.row.id,0,t.$index)}}},[e._v("启用")]):a("el-button",{staticClass:"el-button--danger-text",attrs:{type:"text"},on:{click:function(a){return e.operateForConfirm(t.row.id,1,t.$index)}}},[e._v("冻结")]),e._v(" "),a("el-button",{staticClass:"el-button--success-text",attrs:{type:"text"},on:{click:function(a){return e.operateForView(t.row)}}},[e._v("查看")])]}}])})],1)],1),e._v(" "),a("el-pagination",{attrs:{layout:"total, sizes, prev, pager, next, jumper","current-page":e.listPage.currentPage,"page-size":e.pageSize,total:e.total},on:{"current-change":e.pageChange,"update:currentPage":function(t){return e.$set(e.listPage,"currentPage",t)},"update:current-page":function(t){return e.$set(e.listPage,"currentPage",t)}}})],1)},staticRenderFns:[]},h={name:"commn-editor",props:["title","fileds","table","submitSuccess"],data:function(){return{dialogFormVisible:!1,formLabelWidth:"80px",entry:{},form:{},rules:{}}},methods:{view:function(e){this.dialogFormVisible=!0,this.entry=e,this.refresh()},refresh:function(){for(var e=0;e<this.fileds.length;e++){var t=this.fileds[e];t.rule&&(this.rules[t.name]=t.rule);var a=t.value;this.entry&&void 0!==this.entry[t.name]&&(a=this.entry[t.name]),this.$set(this.form,t.name,a)}}}},v={render:function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("el-dialog",{ref:"dialog",attrs:{title:e.title,visible:e.dialogFormVisible,width:"442px"},on:{"update:visible":function(t){e.dialogFormVisible=t}}},[a("el-form",{ref:"commForm",attrs:{model:e.form,rules:e.rules,"label-position":"left"}},[a("el-row",{attrs:{gutter:22}},[a("el-col",{attrs:{span:12}},[a("el-form-item",{attrs:{label:"用户名"}},[e._v(e._s(e.form.name))]),e._v(" "),a("el-form-item",{attrs:{label:"密码"}},[e._v(e._s(e.form.password))]),e._v(" "),a("el-form-item",{attrs:{label:"手机号"}},[e._v(e._s(e.form.phone))]),e._v(" "),a("el-form-item",{attrs:{label:"性别"}},[e._v(e._s(e.form.sex?"女":"男"))])],1),e._v(" "),a("el-col",{attrs:{span:12}},[a("el-form-item",{attrs:{label:"实名认证"}},[e._v(e._s(e.form.is_certification?"已认证":"未认证"))]),e._v(" "),a("el-form-item",{attrs:{label:"身份认证"}},[e._v(e._s(e.form.is_identity_authentication?"已认证":"未认证"))]),e._v(" "),a("el-form-item",{attrs:{label:"账号状态"}},[e._v(e._s(e.form.status?"冻结":"正常"))]),e._v(" "),a("el-form-item",{attrs:{label:"用户标识"}},[e._v(e._s(e.form.flag?"自媒体人":"普通用户"))])],1)],1)],1)],1)},staticRenderFns:[]};var b={name:"ChannelManager",data:function(){return{params:{name:"AP_USER",page:1,size:10,where:[]},total:0,host:"",list:[],fileds:[{list:!0,label:"账号",name:"name",type:"input",placeholder:"请输入用户名",rule:[{required:!0,message:"请输入用户名",trigger:"blur"},{min:5,max:20,message:"用户名在5~20个字符",trigger:"blur"}]},{list:!1,label:"密码",name:"password",type:"input",placeholder:"请输入密码"},{list:!0,label:"手机号",name:"phone",type:"input",placeholder:"请输入手机号"},{list:!0,label:"性别",name:"sex",type:"radio",value:1,radios:[{value:0,label:"男"},{value:1,label:"女"},{value:2,label:"未知"}]},{list:!0,label:"实名认证",name:"is_certification",type:"radio",value:0,radios:[{value:0,label:"否"},{value:1,label:"是"}]},{list:!0,label:"身份认证",name:"is_identity_authentication",type:"radio",value:0,radios:[{value:0,label:"否"},{value:1,label:"是"}]},{list:!0,label:"账号状态",name:"status",type:"radio",value:0,radios:[{value:0,label:"正常"},{value:1,label:"冻结"}]},{list:!0,label:"用户标识",name:"flag",type:"radio",value:0,radios:[{value:0,label:"普通用户"},{value:1,label:"自媒体人"},{value:2,label:"大V"}]},{list:!0,label:"注册时间",name:"created_time",type:"hidden",value:f.a.format13HH((new Date).getTime())}]}},mounted:function(){this.loadData()},components:{SearchTool:c,SearchResult:a("VU/8")(d,p,!1,null,null,null).exports,Editor:a("VU/8")(h,v,!1,function(e){a("paTC")},"data-v-cc7fffb2",null).exports},methods:{viewData:function(e){this.$refs.editor.view(e)},addData:function(e){this.$refs.editor.add()},submitSuccess:function(){this.loadData()},changeStatus:function(e,t){this.$set(this.list[e],"status",t)},changeParam:function(e){this.params.page=1,this.params.where=e,this.loadData()},changePage:function(e){this.params.page=e.page,this.loadData()},loadData:function(){var e=this;return o()(n.a.mark(function t(){var a;return n.a.wrap(function(t){for(;;)switch(t.prev=t.next){case 0:return t.next=2,Object(m.a)(l()({},e.params));case 2:200===(a=t.sent).code?(e.list=a.data.list,e.host=a.host,e.total=a.data.total):e.$message({type:"error",message:a.errorMessage});case 4:case"end":return t.stop()}},t,e)}))()}}},g={render:function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",[a("search-tool",{attrs:{changeParam:e.changeParam,addData:e.addData}}),e._v(" "),a("search-result",{ref:"mySearchResult",attrs:{list:e.list,host:e.host,total:e.total,table:this.params.name,viewData:e.viewData,changePage:e.changePage,changeStatus:e.changeStatus,fileds:e.fileds,pageSize:e.params.size}}),e._v(" "),a("Editor",{ref:"editor",attrs:{fileds:e.fileds,title:"用户详情",table:this.params.name,submitSuccess:e.submitSuccess}})],1)},staticRenderFns:[]},_=a("VU/8")(b,g,!1,null,null,null);t.default=_.exports},paTC:function(e,t){},t5DY:function(e,t,a){"use strict";t.a=function(e){return new r.a({url:n.k,method:"post",data:e})},t.b=function(e){return new r.a({url:n.l,method:"post",data:e})};var r=a("vLgD"),n=a("2EN7")}});
//# sourceMappingURL=6.9fb26a3bcb5b57ae5b33.js.map