package com.hotusm.fm;

import java.io.Serializable;
import java.util.List;

/**
 * 鑿滃崟Entity
 * @author lanzhm
 * @version 2016-05-03
 */
public class Menu implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String id; // id
	
	private String code; // 缂栫爜
	
	private String name; 	// 鍚嶇О
	
	private String parentId; // 鐖惰妭鐐�
	
	
	private String parentIds; // 鎵�湁鐖剁骇缂栧彿
	
	private Integer sort; 	// 鎺掑簭
	
	private String href; 	// 閾炬帴
	
	private String target; 	// 鐩爣锛�mainFrame銆乢blank銆乢self銆乢parent銆乢top锛�
	
	private String iconFileName; 	// 鍥炬爣key
	
	private String iconOriginalName; 	// 鍥炬爣鍘熷鍚嶇О
	
	private String isShow; 	// 鏄惁鍦ㄨ彍鍗曚腑鏄剧ず锛�锛氭樉绀猴紱0锛氫笉鏄剧ず锛�
	
	private String permission; // 鏉冮檺鏍囪瘑
	
	private String dr; // 鍒犻櫎鏍囪瘑
	
	private String remark;
	
	/**
	 * @modifier Hotusm
	 * @since 2016-05-04
	 * 淇敼瀵瑰簲鍏崇郴
	 */
	
	public String getId() {
		return id;
	}

	public Menu() {
		super();
	}

	public Menu(String id, String name, String parentId, String parentIds,
			Integer sort, String iconFileName) {
		super();
		this.id = id;
		this.name = name;
		this.parentId = parentId;
		this.parentIds = parentIds;
		this.sort = sort;
		this.iconFileName = iconFileName;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getParentIds() {
		return parentIds;
	}

	public void setParentIds(String parentIds) {
		this.parentIds = parentIds;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getIsShow() {
		return isShow;
	}

	public void setIsShow(String isShow) {
		this.isShow = isShow;
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

	public String getDr() {
		return dr;
	}

	public void setDr(String dr) {
		this.dr = dr;
	}


	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
	public String getIconFileName() {
		return iconFileName;
	}

	public void setIconFileName(String iconFileName) {
		this.iconFileName = iconFileName;
	}

	public String getIconOriginalName() {
		return iconOriginalName;
	}

	public void setIconOriginalName(String iconOriginalName) {
		this.iconOriginalName = iconOriginalName;
	}

	public static void sortList(List<Menu> list, List<Menu> sourcelist, String parentId, boolean cascade){
		for (int i=0; i<sourcelist.size(); i++){
			Menu e = sourcelist.get(i);
			if (e.getParentId()!=null && e.getParentId()!=null
					&& e.getParentId().equals(parentId)){
				list.add(e);
				if (cascade){
					// 鍒ゆ柇鏄惁杩樻湁瀛愯妭鐐� 鏈夊垯缁х画鑾峰彇瀛愯妭鐐�
					for (int j=0; j<sourcelist.size(); j++){
						Menu child = sourcelist.get(j);
						if (child.getParentId()!=null&& child.getParentId().equals(e.getId())){
							sortList(list, sourcelist, e.getId(), true);
							break;
						}
					}
				}
			}
		}
	}
}