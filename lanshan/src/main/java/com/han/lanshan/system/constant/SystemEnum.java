package com.han.lanshan.system.constant;

/**
 * 
 * 描述： 常用枚举 创建人：HYD 创建时间：2019年9月8日 下午4:18:46 修改人：HYD 修改时间：2019年9月8日 下午4:18:46
 * 修改备注：
 * 
 * @version
 */
public class SystemEnum {
	/**
	 * 菜单是否是按钮
	 * 
	 * @author my
	 *
	 */
	public static enum MenuType {
		菜单("1"), 按钮("2");
		private String value;

		private MenuType(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}

	}

	/**
	 * 是否可用
	 * 
	 * @author my
	 *
	 */
	public static enum ActiveEnum {
		可用("1"), 不可用("2");
		private String value;

		private ActiveEnum(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}

	}
}
