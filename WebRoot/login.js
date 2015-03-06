Ext.onReady(function() {
	Ext.create('Ext.window.Window', {
		title : '投票管理系统登录入口',
		width : 330,
		modal : true,
		layout : 'fit',
		closable : false,
		titleAlign : 'center',
		items : {
			xtype : 'form',
			padding : '20 20 20 20',
			border : false,
			defaults : {
				anchor : '85%',
				labelWidth : 60,
				labelAlign : 'right',
				allowBlank : false
			},
			items : [{
						xtype : 'combo',
						name : 'rylb',
						fieldLabel : '用户类型',
						queryMode : 'local',
						editable:false,
						store : ['管理员', '普通用户']
					}, {
						xtype : 'textfield',
						name : 'dlm',
						fieldLabel : '用户名'
					}, {
						xtype : 'textfield',
						name : 'mm',
						fieldLabel : '密码',
						emptyText : '密码',
						inputType : 'password'
					}]
		},
		buttons : [{
			text : '登录',
			listeners : {
				click : {
					fn : function(button) {
						console.log('登录');
						if (button.up('window').down('form').isValid()) {
							var rylb = button.up('window')
									.down('textfield[name=rylb]').getValue();
							var dlm = button.up('window')
									.down('textfield[name=dlm]').getValue();
							var mm = button.up('window')
									.down('textfield[name=mm]').getValue();
							Ext.Ajax.request({
										url : 'dengLuAction',
										method : 'GET',
										params : {
											rylb:rylb,
											dlm : dlm,
											mm : mm
										},
										success : function(res, o) {
											var rt = Ext.JSON
													.decode(res.responseText);
											Ext.Msg.alert("提示", rt.msg);
											if (rt.msg == "管理员登陆成功！") {
												window.location.href = "app.jsp";
											}
											if (rt.msg == "普通用户登陆成功！") {
												window.location.href = "vote.jsp";
											}
										},
										failure : function(res, o) {
											var rt = Ext.JSON
													.decode(res.responseText);
											Ext.Msg.alert("提示", rt.msg);
										}
									});
						} else {
							Ext.Msg.alert("提示", "用户名或密码无效!");
						}

					}
				}
			}
		}, {
			text : '注册',
			listeners : {
				click : {
					fn : function(button) {
						var regwin = Ext.create('widget.regwin');
						var rylxcombo = regwin.down('combo[name=rylb]');
						rylxcombo.setValue('普通用户');
						rylxcombo.hide();
						regwin.down('button[text=保存]').on('click', function(but) {
							var form = but.up('window').down('form').getForm();
							if (form.isValid()) {
								form.submit({
											success : function(form, action) {
												regwin.close();
												Ext.Msg.alert('成功',
														action.result.msg);
											},
											failure : function(form, action) {
												Ext.Msg.alert('失败',
														action.result.msg);
											}
										});
							}
						})
						regwin.show();
					}
				}
			}
		}]
	}).show();
});
