<template>
	<div>
		<el-container>
		  <el-header>
			  <img class="mlogo" src="https://www.markerhub.com/dist/images/logo/markerhub-logo.png" alt="">
		  </el-header>
		  <el-main>
			  <el-form :model="LoginForm" :rules="rules" ref="LoginForm" label-width="100px" class="demo-LoginForm">
			    <el-form-item label="用户名" prop="username">
			      <el-input v-model="LoginForm.username"></el-input>
			    </el-form-item>
				<el-form-item label="密码" prop="password">
				  <el-input type="password" v-model="LoginForm.password"></el-input>
				</el-form-item>
			    <el-form-item>
			      <el-button type="primary" @click="submitForm('LoginForm')">登录</el-button>
			      <el-button @click="resetForm('LoginForm')">重置</el-button>
			    </el-form-item>
			  </el-form>
		  </el-main>
		</el-container>
	</div>
</template>

<script>
export default{
	name:'Login',
	data() {
		return {
			LoginForm: {
				username: 'admin',
				password: '111111',
			},
			rules: {
				username: [
				{ required: true, message: '请输入用户名', trigger: 'blur' },
				{ min: 5, max: 10, message: '长度在 5 到 10 个字符', trigger: 'blur' }
				],
				password: [
				{ required: true, message: '请输入密码', trigger: 'blur' },
				{ min: 5, max: 12, message: '长度在 5 到 12 个字符', trigger: 'blur' }
				]
			}
		}
	},
	    methods: {
	      submitForm(formName) {
	        this.$refs[formName].validate((valid) => {
	          if (valid) {
				  const _this = this
	            this.$axios.post('/login',this.LoginForm).then(res =>{
					const jwt = res.headers['authorization']
					const userInfo = res.data.data
					//把数据共享
					_this.$store.commit("SET_TOKEN",jwt)
					_this.$store.commit("SET_USERINFO",userInfo)
					
					//获取
					console.log(_this.$store.getters.getUser)
					//跳转
					_this.$router.push("/blogs")
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
	    }
}
</script>

<style scoped>
	.el-header, .el-footer {
		background-color: #B3C0D1;
		color: #333;
		text-align: center;
		line-height: 60px;
	}

	.el-aside {
		background-color: #D3DCE6;
		color: #333;
		text-align: center;
		line-height: 200px;
	}

	.el-main {
		/* background-color: #E9EEF3; */
		color: #333;
		text-align: center;
		line-height: 160px;
	}

	body > .el-container {
		margin-bottom: 40px;
	}

	.el-container:nth-child(5) .el-aside,
	.el-container:nth-child(6) .el-aside {
		line-height: 260px;
	}

	.el-container:nth-child(7) .el-aside {
		line-height: 320px;
	}
	.mlogo {
		height: 60%;
		margin-top: 13px;
	}
	.demo-LoginForm {
		max-width: 500px;
		margin: 0 auto;
	}
</style>
