Ext.define('TPGLXT.controller.toupiaoDXGL', {
	extend : 'Ext.app.Controller',
	stores : ['toupiaoDXGL'],
	views : ['toupiaoDXGL', 'register'],
	onButtonClick : function(button, e, eOpts) {
		if (button.text == '添加') {
			var win = Ext.create('widget.regwin');
			var rylxcombo = win.down('combo[name=rylb]');
			var dlmfield = win.down('textfield[name=dlm]');
			var mmfield = win.down('textfield[name=mm]');
			rylxcombo.setValue('投票对象');
			dlmfield.disable();
			mmfield.disable();
			dlmfield.hide();
			mmfield.hide();
			rylxcombo.hide();
			win.down('button[text=保存]').on('click', function(but) {
						var form = but.up('window').down('form').getForm();
						if (form.isValid()) {
							form.submit({
										success : function(form, action) {
											button.up('grid').getStore()
													.reload();
											win.close();
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
			win.show();
		} else if (button.text == '修改') {
			if (button.up('grid').getSelectionModel().hasSelection()) {
				var win = Ext.create('widget.regwin');
				var rylxcombo = win.down('combo[name=rylb]');
				var dlmfield = win.down('textfield[name=dlm]');
				var mmfield = win.down('textfield[name=mm]');
				dlmfield.disable();
				mmfield.disable();
				dlmfield.hide();
				mmfield.hide();
				rylxcombo.hide();
				var record = button.up('grid').getSelectionModel()
						.getSelection()[0];
				win.down('form').loadRecord(record);
				win.down('button[text=保存]').on('click', function(but) {
							var form = but.up('window').down('form').getForm();
							if (form.isValid()) {
								form.submit({
											params : {
												ryid : record.data.ryid
											},
											success : function(form, action) {
												button.up('grid').getStore()
														.reload();
												win.close();
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
				win.show();
			} else {
				Ext.Msg.alert("提示", "请先选择一条记录！");
			}

		} else if (button.text == '删除') {
			if (button.up('grid').getSelectionModel().hasSelection()) {
				Ext.Msg.confirm('提示', '确定删除所选中的对象吗?', function(btn) {
							if (btn == 'yes') {
								var ryid = button.up('grid')
										.getSelectionModel().getSelection()[0].data.ryid;
								var store = button.up('grid').getStore();
								Ext.Ajax.request({
											url : 'shanChuYHandDHAction',// 这里url为投票对象的"删除"
											params : {
												ryid : ryid
												// 传送要删除的ryid
											},
											success : function(response, opts) {
												store.load();
												var rt = Ext
														.decode(response.responseText);
												Ext.Msg.alert("提示", rt.msg);
											},
											failure : function(response, opts) {
												Ext.Msg.alert("提示", "服务器错误！");
											}
										})
							}
						})
			} else {
				Ext.Msg.alert("提示", "请先选择一条记录！");
			}

		} else {
			if (button.up('toolbar').down('textfield').getValue()) {
				button.up('grid').getStore().load({
					params : {// 这里传的是查找框里的内容,后台接收"query"参数
						query : button.up('toolbar').down('textfield')
								.getValue()
					}
				})
			}
		}
	},
	init : function(application) {
		this.control({
					"toupiaoDXGLPanel button" : {
						click : this.onButtonClick
					}
				});
	}

});