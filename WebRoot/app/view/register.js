Ext.define('TPGLXT.view.register', {
			extend : 'Ext.window.Window',
			alias : 'widget.regwin',
			title : '投票管理系统注册窗口',
			width : 400,
			modal : true,
			layout : 'fit',
			titleAlign : 'center',
			items : {
				xtype : 'form',
				padding : '20 20 20 20',
				border : false,
				defaultType : 'textfield',
				url : 'zhuCeYHandDXAction',
				defaults : {
					anchor : '90%',
					labelWidth : 60,
					labelAlign : 'right',
					allowBlank : false
				},
				items : [{
							name : 'xm',
							fieldLabel : '姓名'
						}, {
							name : 'xh',
							fieldLabel : '学号'
						}, {
							name : 'zy',
							fieldLabel : '专业'
						}, {
							name : 'dlm',
							fieldLabel : '登录名'
						}, {
							name : 'mm',
							fieldLabel : '密码'
						}, {
							name : 'lxfs',
							fieldLabel : '联系方式'
						}, {
							xtype : 'combo',
							name : 'rylb',
							fieldLabel : '人员类型',
							store : ['投票对象', '普通用户']
						}]
			},
			buttons : [{
						text : '保存'
					}, {
						text : '取消',
						listeners : {
							click : {
								fn : function(button) {
									button.up('window').close();
								}
							}
						}
					}]
		})
