Ext.define('TPGLXT.view.yonghuGL', {
			extend : 'Ext.panel.Panel',
			alias : 'widget.yonghuGLPanel',
			title : '投票用户管理',
			layout : 'fit',
			initComponent : function() {
				var me = this;
				Ext.applyIf(me, {
							items : [{
										xtype : 'gridpanel',
										autoScroll : true,
										store : 'yonghuGL',
										columns : [{
													xtype : 'gridcolumn',
													dataIndex : 'xm',
													text : '姓名'
												}, {
													xtype : 'gridcolumn',
													dataIndex : 'xh',
													text : '学号'
												}, {
													xtype : 'gridcolumn',
													dataIndex : 'zy',
													text : '专业'
												}, {
													xtype : 'gridcolumn',
													dataIndex : 'lxfs',
													text : '联系方式'
												}, {
													xtype : 'gridcolumn',
													dataIndex : 'sftp',
													text : '是否投票'
												}],
										dockedItems : [{
													xtype : 'toolbar',
													dock : 'top',
													items : [{
																xtype : 'button',
																text : '添加'
															}, {
																xtype : 'button',
																text : '修改'
															}, {
																xtype : 'button',
																text : '删除'
															}, {
																xtype : 'tbseparator'
															}, '->',{
																xtype : 'textfield'
															},  {
																xtype : 'button',
																text : '开始查找'
															}]
												}]
									}]
						});
				me.callParent(arguments);
			}
		});