package objectrepositories;

public class RUSConstants {
	

	public static final String user_text="//input";
	public static final String pwd_text="//tr[3]/td[2]/input";
	public static final String login_button="//tr[5]/td/input";
	public static final String logout="//button";
	public static final String task="//span[contains(text(),'Tasks')]";
	public static final String mytask="//div[contains(text(),'My tasks')]";
	public static final String mytask_desc_first_record=".//*[@id='task_table']/div[2]/div[1]/table/tbody/tr[1]/td[3]/div/div";
	public static final String mytask_createdon=".//*[@id='task_table']/div[1]/div[1]/div/table/tbody/tr/td[13]/div[3]";
	
	
	public static final String task_not_accepted=".//*[@id='task_tree']/div[2]/div[1]/table/tbody/tr[3]/td/div/div/div";
	public static final String is_ecom_validation="//div[contains(text(),'New Product Validation For USA - Is eCommerce product')]";
	public static final String ecom_enrichment_task="//div[contains(text(),'New Product Development - eCommerce Enrichment for eContent Team')]";
	public static final String ecom_enrichment_approved_task="//div[contains(text(),'New Product Development - eCommerce Enrichment Approval for eContent Team')]";
	public static final String bb_enrichment_task="//div[contains(text(),'New Product Development - Brand Building Enrichment for eCommerce Marketing Team')]";
	public static final String bb_manager_enrichment_task="//div[contains(text(),'New Product Development - Brand Building Enrichment Approval for eCommerce Marketing Team')]";
	public static final String update_assortment_task="//div[contains(text(),'Update Assortment Information')]";
	public static final String update_rpc_task="//div[contains(text(),'Update Retailer Product Code information')]";
	
	
	public static final String list_view_lastcreated_on=".//*[@id='article_table']/div[1]/div[1]/div/table/tbody/tr/td[16]/div[3]";//"//div[contains(text(),'Last changed on')]";
	public static final String ecom="//div[contains(text(),'eCommerce View ')]";
	public static final String ECOM_TAB_Isecom="//td[.//div[.//span[contains(text(),'Is Ecom Product:')]]]/following-sibling::td[contains(@class,'v-formlayout-contentcell')]/div/div/div[contains(@class,'v-panel-captionwrap')]/following-sibling::div[1]/div/div";
	public static final String ECOM_TAB_ecomrichment_complete="//td[.//div[.//span[contains(text(),'eCom Enrichment Complete:')]]]/following-sibling::td[contains(@class,'v-formlayout-contentcell')]/div/div/div[contains(@class,'v-panel-captionwrap')]/following-sibling::div[1]/div/div";
	public static final String ECOM_TAB_ecom_enrichment_approved="//td[.//div[.//span[contains(text(),'eCom Enrichment Approved:')]]]/following-sibling::td[contains(@class,'v-formlayout-contentcell')]/div/div/div[contains(@class,'v-panel-captionwrap')]/following-sibling::div[1]/div/div";
	public static final String BB_TAB_bbenrichment_complete="//td[.//div[.//span[contains(text(),'BB Enrichment Complete:')]]]/following-sibling::td[contains(@class,'v-formlayout-contentcell')]/div/div/div[contains(@class,'v-panel-captionwrap')]/following-sibling::div[1]/div/div";
	public static final String BB_TAB_bb_enrichment_approved="//td[.//div[.//span[contains(text(),'BB Enrichment Approved:')]]]/following-sibling::td[contains(@class,'v-formlayout-contentcell')]/div/div/div[contains(@class,'v-panel-captionwrap')]/following-sibling::div[1]/div/div";
	public static final String refresh_button="//div[3]/div/span[2]";
	public static final String BB_tab_us_bb_task="//div[contains(text(),'Brand Building View')]";//Brand Building View
	public static final String ECOM_TAB_reatiler_attribute=".//*[@id='article_ECommerceView_tab']/div/div/div/div[1]/div/table/tbody/tr[1]/td[3]/div/div";
	public static final String ECOM_TAB_retailerstart_date="//td[.//div[.//span[contains(text(),'Start Availability Date:')]]]/following-sibling::td[contains(@class,'v-formlayout-contentcell')]/div/div/div[contains(@class,'v-panel-captionwrap')]/following-sibling::div[1]/div/div";
	public static final String ECOM_TAB_retailer_product_code_updated="//td[.//div[.//span[contains(text(),'Retailer Product Code Updated:')]]]/following-sibling::td[contains(@class,'v-formlayout-contentcell')]/div/div/div[contains(@class,'v-panel-captionwrap')]/following-sibling::div[1]/div/div";
	public static final String ECOM_TAB_retailer_data_updated="//td[.//div[.//span[contains(text(),'Retailer Data Updated:')]]]/following-sibling::td[contains(@class,'v-formlayout-contentcell')]/div/div/div[contains(@class,'v-panel-captionwrap')]/following-sibling::div[1]/div/div";
	
	
}
