<template>
	<div>
		<Header></Header>
		<div class="m-content">
			<el-form :model="BlogForm" :rules="rules" ref="BlogForm" label-width="100px" class="BlogForm">
			  <el-form-item label="标题" prop="title">
			    <el-input v-model="BlogForm.title"></el-input>
			  </el-form-item>
			  
			  <el-form-item label="摘要" prop="description">
			    <el-input type="textarea" v-model="BlogForm.description"></el-input>
			  </el-form-item>
			  
			  <el-form-item label="内容" prop="content">
			    <mavon-editor v-model="BlogForm.content"></mavon-editor>
			  </el-form-item>
			  
			  <el-form-item>
			    <el-button type="primary" @click="submitForm('BlogForm')">立即发表</el-button>
			    <el-button @click="resetForm('BlogForm')">重置</el-button>
			  </el-form-item>
			</el-form>
		</div>
	</div>
</template>

<script>
import Header from '../components/Header'
export default{
	name:'BlogEdit',
	components:{
		Header
	},
	data() {
		return {
			BlogForm: {
				id:'',
				title: '',
				description: '',
				content: ''
			},
			rules: {
				title: [
				{ required: true, message: '请输入博客标题', trigger: 'blur' },
				{ min: 5, max: 25, message: '长度在 5 到 25 个字符', trigger: 'blur' }
				],
				description: [
				{ required: true, message: '请输入博客摘要', trigger: 'blur' }
				],
				content: [
				{ required: true, message: '请输入博客内容', trigger: 'blur' }
				],
			}
		}
	},
	methods: {
		submitForm(formName) {
			this.$refs[formName].validate((valid) => {
			if (valid) {
				const _this = this
				this.$axios.post("/blog/edit",this.BlogForm,{
					headers:{
						"Authorization":localStorage.getItem("token")
					}
				}).then(res => {
					console.log(res)
					_this.$alert('发表成功','提示',{
						confirmButtonText:'确定',
						callback: action => {
							_this.$router.push("/blogs")
						}
					})
				})
			} else {
				console.log('error submit!!');
				return false;
			}
			});
		},
		resetForm(formName) {
			this.$refs[formName].resetFields();
		}
	},
	created(){
		const blogId = this.$route.params.blogId
		console.log(blogId)
		const _this = this
		if(blogId){
			this.$axios.get("/blog/" + blogId).then(res => {
				const blog = res.data.data
				_this.BlogForm.id = blog.id
				_this.BlogForm.title = blog.title
				_this.BlogForm.description = blog.description
				_this.BlogForm.content = blog.content
				
			})
		}
		
	}
}
</script>

<style scoped>
	.m-content{
		text-align: center;
	}
</style>
