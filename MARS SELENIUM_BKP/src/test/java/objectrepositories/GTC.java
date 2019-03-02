package objectrepositories;

// import org.openqa.selenium.By;

public class GTC
{

	//  ##### Login Page Objects #####
	
	public static final String TXT_LOGIN_USER_ID = "//input[@id='userName']";   
	public static final String TXT_LOGIN_PWD = "//input[@id='password']";   
	public static final String BTN_LOGIN = "//input[@id='login-button']";
	public static final String TXT_LOGIN_USER_txt="//label[contains(text(),'User name:')]"; // need to change
	
	public static final String BTN_Search = "//div[3]/div/input";
	//public static final By TXT_USERID = By.id("userName");
	
	//public static final String BTN_LOGOUT = "//button[@id='link_logout']";
	public static final String BTN_LOGOUT = "//span[contains(text(),'Log out')]";
	public static final String BTN_LOGOUT_FR="//span[contains(text(),'Déconnexion')]";
 
	public static final String TAB_TASKS = "//span[contains(text(),'Tasks')]";
	public static final String TAB_MY_TASK = "//div[contains(text(),'My tasks')]";
	public static final String TAB_TASK_NOT_ACCEPTED = "//div[contains(text(),'Tasks not accepted')]";
	public static final String TAB_MY_RESPONSIBILITY = "//div[contains(text(),'My responsibilities')]";
	//public static final String TAB_QUERIES = "//span[contains(text(),'Queries')]";
											 
	public static final String TAB_QUERIES = ".//*[@id='view.container.context.iconstrip']/div/div/div[1]/div/div/div/div/div/div/div/div[2]/button";
	//public static final String TAB_SEARCH = "//span[contains(text(),'Search')]";
	public static final String TAB_SEARCH = "//span[contains(text(),'Search')]";
	public static final String TAB_DASHBOARD = "//span[contains(text(),'Dashboard')]";
	
	public static final String DIV_TABLE_VIEW_SEARCH = "//div[contains(text(),'Table view')]";
	
	public static final String TASK_ECOMM_TEAM_MANAGER = "//div[contains(text(),'eCommerce Team Manager - China')]";
	
	public static final String FIRST_TASK = "//td[3]/div/div";
	
	public static final String FIRST_PRODUCT_TASK = "//div[2]/div/table/tbody/tr/td[2]/div";
	
	public static final String SECOND_PRODUCT_TASK = "//div[2]/div/table/tbody/tr[2]/td[5]/div";
	
	
	public static final String BTN_FULLTEXT_SEARCH = "//div[@id='fulltextsearch_button']/span";
	
	public static final String TXT_FULLTEXT_SEARCH = "search_input_textfield";
	
	public static final String SRCH_PROD_COUNT = "//div[2]/div/div/div/div/div/button";
	
	public static final String lan_Sel =".//*[@id='language']";
	
	public static final String VER_ATR_CHI = ".//*[@id='MainLayout']/div/div[2]/div/div/div[3]/div/div[2]/div/div/div[1]/div/div/div/div";
	
    public static final String QRY_CLK = ".//*[@id='view.container.context.iconstrip']/div/div/div[1]/div/div/div/div/div/div/div/div[2]/button";
    
    public static final String ITM_DRPDWN = "//*[@id='search-select-menu-%entity.Article.name']/span";
    public static final String Prd_DRPDWN = "//div[3]/div/div/div[2]/div/div/div/div/div/div/span";
	public static final String ITM_SELC = "//span[text()='Items in a catalog']";
	
	public static final String SRCH_BTN = "//div[9]/div/span/span";
	
	public static final String PRDCT_CLK = ".//*[@id='article_table']/div[2]/div[1]/table/tbody/tr[1]/td[5]/div";
	
	public static final String GEN_VIEW = "//td[5]/div/div/div";
	
	public static final String PRD_TLTIP = "//tr[7]/td[3]/div/div/div[2]/div/div";
	
	
	public static final String BB_VIEW = "//div[contains(text(),'Brand Building View')]";
	
	public static final String Target_Market = "//tr[2]/td[3]/div/div";
	public static final String Target_Market1 = "//div[2]/div/div/div/div/div/div/div/div/table/tbody/tr/td[3]/div/div";
	
	public static final String SLCT_CHINA = "//span[text()='China']";
	
	public static final String DRPDOWN = "//tr[2]/td[3]/div/div";
	
	public static final String Ecom_VIEW = "//div[contains(text(),'eCommerce View')]";
	
	public static final String EcomDrop = "//div[2]/div/div/div/div/div/div/div/div/table/tbody/tr/td[3]/div/div";
	
	public static final String Reatiler_range ="//div[2]/div/div/div/div/div/div/div/div/table/tbody/tr/td[3]/div/div";
	
	public static final String Retalier_range2 = "//tr[2]/td[3]/div/div";
	
	public static final String Assortment_No =".//*[@id='VAADIN_COMBOBOX_OPTIONLIST']/div/div[2]/table/tbody/tr[5]/td";
	
	public static final String Retailer_Flipkart = "//span[text()='AZ']";
	
	public static final String Retai_Desc = "//tr[10]/td[3]/div/div/div[2]/div/div";
	
	public static final String REF_Type = "//div[contains(text(),'References')]";
	
	public static final String REF_Item ="//div[2]/div/div[2]/div/table/tbody/tr/td[3]/div";
	
	public static final String TAB_STRUCTURES = "//span[contains(text(),'Structures')]";
	
	public static final String Struc_Drop = ".//*[@id='system_select_menu']/div";
	
	public static final String Unil_gpc = "//span[text()='UnileverGPC']";
	
	public static final String Tree =".//*[@id='structure_tree']/div[2]/div[1]/table/tbody/tr[2]/td[1]/div/span"; 
	
	public static final String Tree1 =".//*[@id='structure_tree']/div[2]/div[1]/table/tbody/tr[3]/td[1]/div/span";
	
	public static final String Tree2 =".//*[@id='structure_tree']/div[2]/div[1]/table/tbody/tr[4]/td[1]/div/span";  
	
	public static final String Tree3 = "//tr[9]/td/div";
	
	public static final String TREE4 ="//tr[4]/td/div/span";
	
	public static final String ITM_ASRTMNT = "//span[text()='Items in assortment (Master catalog)']";
	
	public static final String ASSORTMENT_LIST =".//*[@id='search']/div/div[2]/div/div/div[1]/div/div[2]/div/div[2]/div/div/div[1]/div/div[2]/div";
	
	public static final String IMAGE_PRESENT = "//div[2]/div/img";
	
	public static final String MEDIA_VIEW = "//div[contains(text(),'Media')]";
	
	public static final String IMAGE_MEDIA_VIEW = "//div[2]/div/div/div/div/img";
	
	public static final String MEDIA_DROPDOWN ="//div[2]/div/div/div[2]/div/div/div/div/div/div/div/div/div/div[2]/div/div/div/table/tbody/tr/td[3]/div/div";
	
	public static final String MEDIA_CHINESE = "//span[text()='Chinese']";
	
	public static final String SHOW_ALL_ENRICHMENT_VIEW = "//td[3]/div/span/span";
	
	public static final String eCOMMENRICHMENT_APPROVE_YES ="//tr[14]/td[3]/div/div/div[2]/div/div";
	
	public static final String ITEM_IN_XML = "//div[2]/div/div[2]/div/div[2]/div/div";
	
	public static final String ECOMM_MANGER_COMMENTS = "//tr[17]/td[3]/div/div/div[2]/div/div";
	
	public static final String ECOMM_ENRICHMENT_APPROVED_NO = "//div[2]/div/table/tbody/tr[15]/td[3]/div/div/div[2]/div/div";
	
	public static final String NO = "//span[text()='No']";
	
	public static final String  SERACH_BUTTON_XPATH = "//div[@id='search']/..//span[contains(text(),'Search')]";
	
	public static final String ACTION_BUTTON_XPATH = "//span[contains(text(),'Actions')]";
	public static final String Response_Pth="//div[@id='pimwebaccess-1169917604-overlays']/div[3]/div/div/div[3]/div/div/div/div/div/div[2]/div/div/div";
	
	public static final String CREATE_TASK_XPATH = ".//*[@id='pimwebaccess-1169917604-overlays']/div[2]/div/div/span[8]/span";
	
	public static final String TASK_NAME_XPATH = "//*[@id='Task.Name']";
	
	public static final String TASK_DESC_XPATH = "//*[@id='Task.Description']";
	
	public static final String TASK_USER_XPATH = "//*[@id='Task.User']/div";

	public static final String TASK_USER_INPUT_XPATH = ".//*[@id='Task.User']/input";
	
	public static final String TASK_USER_INPUT_DROP_XPATH = ".//*[@id='VAADIN_COMBOBOX_OPTIONLIST']/div/div[2]/table/tbody/tr/td";
	
	public static final String TASK_USER_INPUT_DROP_XPATH_2 = ".//*[@id='VAADIN_COMBOBOX_OPTIONLIST']/div/div[2]/table/tbody/tr[1]/td";
	
	public static final String TASK_RESPOSIBLE_INPUT_XPATH = "//div[@id='Task.Responsible']/input";
	
	public static final String CREATE_TASK_OK_XPATH = "//span[contains(text(),'OK')]";
	
	public static final String TASK_LIST_XPATH = "//*[@id='task_table']/div[2]/div/table/tbody/tr"; 
	
	public static final String TASK_LIST_XPATH_FR = "//div[@id='task_table']/div[2]/div/table/tbody/tr"; 
	
	public static final String MARK_AS_COMPLETED_XPATH = "//span[contains(text(),'Mark as completed')]";
	
	public static final String ITEM_LIST_XPATH = "//*[@id='pimwebaccess-1169917604-overlays']/div[2]/div/div/span";
	
	public static final String ITEMs_XPATH=".//*[@id='product.items.table.config.default']/div[2]/div[1]/table/tbody";
	
	public static final String MAIN_LAYOUT_LIST_VIEW_XPATH = "//div[@id='MainLayout']/div/div[2]/div/div/div[3]/div/div[2]/div/div/div[2]/div/div/div/div";
	
	public static final String SHOW_ALL_XPATH = "//span[contains(text(),'Show all')]";
	
	public static final String ECOM_ENRICHMENT_APPROVED_XPATH = "//tr[12]/td[3]/div/div/div[2]/div/div";
	
	public static final String ECOM_MANAGER_COMMENTS_XPATH = "//div[2]/div/table/tbody/tr[13]/td[3]/div/div/div[2]/div/div";
	
	public static final String DASHBOARD_BUTTON_XPATH = "//div[@id='MainLayout']/div/div[2]/div/div/div[3]/div/div[2]/div/div/div/div/div/div[3]/div/span[4]";
	
	public static final String DATAQUALITY_DASHBOARD_VIEW_XPATH = "//div[@id='MainLayout']/div/div[2]/div/div/div[3]/div/div/div/div/div/div/span";
	
	public static final String TASK_BB_TEAM_MANAGER = "//div[contains(text(),'Marketing /BB Manager - Dove HairCare')]";
	
	public static final String TASK_ECOMM_TEAM_TASK = "//div[contains(text(),'eCommerce Team - China')]";  
	
	public static final String NEW_PRODUCT_DEV_ENRICHMENT_FOR_CHINA = "//tr[2]/td[3]/div/div";
	
	public static final String Ecomm_PROD_SET_YES = "//tr[8]/td[3]/div/div/div[2]/div/div";
	
	public static final String ONEVOT_ECOMM_TITLE="//tr[12]/td[3]/div/div/div[2]/div/div"; 
	
	public static final String show_ALL_BB_VIEW ="//td[3]/div/span/span";
	
	public static final String Lang_Specific_Brand_name = "//tr[2]/td[3]/div/div/div[2]/div/div";
	
	public static final String e_SUBBRAND ="//tr[9]/td[3]/div/div/div[2]/div/div";
	
	public static final String e_FunctionalName = "//tr[11]/td[3]/div/div/div[2]/div/div";
	
	public static final String e_Variant = "//tr[10]/td[3]/div/div/div[2]/div/div";
	
	public static final String e_size = "//div[2]/div/table/tbody/tr[3]/td[3]/div/div/div[2]/div/div";
	
	public static final String ITEM_NO = "//div[2]/div/div[2]/div/div[2]/div/div";
	
	public static final String REFRESH ="//div[3]/div/span[2]";
	
	public static final String PSO_SUB_CATEGORY = "//div[2]/div/div/div/div/div/div/div/div/table/tbody/tr/td[3]/div/div";
	
	public static final String PSO_SUB_CATEGORY_LABEL = "//div[2]/div/table/tbody/tr[10]/td/div/span";
	
	public static final String PSO_SUB_CATEGORY_DRP_DWN = ".//*[@id='detail_edit_d340c099fc33ecacfa45b457dbe1d5e2']/div/div[2]/div/div/div";
	
	public static final String PSO_SUB_CATEGORY_DRP_DWN_LIST = ".//*[@id='VAADIN_COMBOBOX_OPTIONLIST']/div/div[2]/table/tbody/tr";
	
	//BB Attributes
	
	public static final String BB_SAP_NAME = "//tr[7]/td[3]/div/div/div[2]/div/div";
	
	public static final String BB_1VOT_ECOMMERCE_TITLE = "//tr[11]/td[3]/div/div/div[2]/div/div";
	
	public static final String BB_BRAND = "//tr[2]/td[3]/div/div/div[2]/div/div";
	
	public static final String BB_LangSpecificBrandName = "//td[3]/div/div/div[2]/div/div";
	
	public static final String BB_SUB_BRAND = "//tr[4]/td[3]/div/div/div[2]/div/div";
	
	public static final String BB_FUNCTIONAL_NAME = "//tr[5]/td[3]/div/div/div[2]/div/div";
	
	public static final String BB_VARIANT = "//tr[6]/td[3]/div/div/div[2]/div/div";
	
	public static final String BB_NET_CONTENT = "//div[2]/div/table/tbody/tr[9]/td[3]/div/div/div[2]/div/div";
	
	public static final String BB_NET_CONTENT_UOM = "//div[2]/div/table/tbody/tr[10]/td[3]/div/div/div[2]/div/div";
	
	public static final String BB_ESUB_BRAND = "//tr[8]/td[3]/div/div/div[2]/div/div";
	
	public static final String BB_EFUNCTIONAL_NAME = "//tr[10]/td[3]/div/div/div[2]/div/div";
	
	public static final String BB_EVARIANT = "//tr[9]/td[3]/div/div/div[2]/div/div";
	
	public static final String BB_ESIZE = "//div[2]/div/table/tbody/tr[3]/td[3]/div/div/div[2]/div/div";
	
	public static final String NAME_LABEL_FOR_TASK = "//span[contains(text(),'Name:')]";
	
	
	public static final String EXECUTE_DQ_RULES = "//span[contains(text(),'Execute data quality rules')]";
	
	public static final String BB_Target_Market = "//span[contains(text(),'Target Market')]";
	
	public static final String BB_FB1 = "//tr[12]/td[3]/div/div/div[2]/div/div";      
	
	public static final String BB_FB2 = "//tr[13]/td[3]/div/div/div[2]/div/div";
	
	public static final String BB_FB3 = "//tr[14]/td[3]/div/div/div[2]/div/div";
	
	public static final String BB_FB4 = "//tr[15]/td[3]/div/div/div[2]/div/div";
	
	public static final String BB_FB5 = "//tr[16]/td[3]/div/div/div[2]/div/div";
	
	public static final String BB_FB6 = "//tr[17]/td[3]/div/div/div[2]/div/div";
	
	public static final String BB_LongFormProductDesc = "//tr[25]/td[3]/div/div/div[2]/div/div";
	
	public static final String BB_LongFormProductDesc_Sync = "//div[2]/div/div/div/div/div/div/div/div/table/tbody/tr[27]/td[3]";
	
	public static final String BB_NumberOneSearchTerm = "//div[2]/div/table/tbody/tr[6]/td[3]/div/div/div[2]/div/div";
	
	public static final String BB_SearchTerms = "//div[2]/div/table/tbody/tr[5]/td[3]/div/div/div[2]/div/div";
	
	public static final String BB_UOM_Types_Drop_Down = "//tr[8]/td[3]/div/input";
	
	public static final String BB_UOM_Types_Drop_Down_NL_And_IN = "//tr[7]/td[3]/div/input";
	
	public static final String BB_SearchKeywordList = "//div[2]/div/table/tbody/tr[4]/td[3]/div/div/div[2]/div/div";
	
	//=================================================
	//Ecomm SAP Attributes
	public static final String Ecomm_Retailer_GLN = "//tr[5]/td[3]/div/div/div[2]/div/div";
	
	public static final String Ecomm_Trade_Item_Group_Desc = "//div[2]/div/table/tbody/tr[5]/td[3]/div/div/div[2]/div/div";
	
	public static final String Ecomm_Category = "//div[2]/div/table/tbody/tr[4]/td[3]/div/div/div[2]/div/div";
	
	public static final String Ecomm_Category_Group = "//tr[7]/td[3]/div/div/div[2]/div/div";
	
	public static final String Ecomm_Market = "//div[2]/div/table/tbody/tr[6]/td[3]/div/div/div[2]/div/div";
	
	public static final String Ecomm_Hazard_Stmt = "//div[2]/div/table/tbody/tr[3]/td[3]/div/div/div[2]/div/div";
	
	public static final String Ecomm_Target_Market = "//span[contains(text(),'Target Market')]";
	
	//Handling and Delivery View SAP Attributes
	
	public static final String Handling_And_Delivery_View = "//div[contains(text(),'Handling and Delivery View')]";
	
	
	public static final String HD_Alternate_Item_Class_Code = "//td[3]/div/div/div[2]/div/div";
	
	public static final String HD_Import_Class_Type = "//tr[3]/td[3]/div/div/div[2]/div/div";
	
	public static final String HD_Import_Class_Value = "//tr[4]/td[3]/div/div/div[2]/div/div";
	
	public static final String HD_Stacking_Factor = "//tr[5]/td[3]/div/div/div[2]/div/div";
	
	
	public static final String HD_DC_Temp_Max = "//tr[7]/td[3]/div/div/div[2]/div/div";
	
	public static final String HD_DC_Temp_Max_UOM = "//tr[8]/td[3]/div/div/div[2]/div/div";
	
	public static final String HD_DC_Temp_Min = "//tr[9]/td[3]/div/div/div[2]/div/div";
	
	public static final String HD_DC_Temp_Min_UOM = "//tr[10]/td[3]/div/div/div[2]/div/div";
	
	
	public static final String HD_Temp_Max = "//tr[11]/td[3]/div/div/div[2]/div/div";
	
	public static final String HD_Temp_Max_UOM = "//tr[12]/td[3]/div/div/div[2]/div/div";
	
	public static final String HD_Temp_Min = "//tr[13]/td[3]/div/div/div[2]/div/div";
	
	public static final String HD_Temp_Min_UOM = "//tr[14]/td[3]/div/div/div[2]/div/div";
	
	
	public static final String HD_Storage_handling_Temp_Max = "//tr[15]/td[3]/div/div/div[2]/div/div";
	
	public static final String HD_Storage_handling_Temp_Max_UOM = "//tr[16]/td[3]/div/div/div[2]/div/div";
	
	public static final String HD_Storage_handling_Temp_Min = "//tr[17]/td[3]/div/div/div[2]/div/div";
	
	public static final String HD_Storage_handling_Temp_Min_UOM = "//tr[18]/td[3]/div/div/div[2]/div/div";
	
	
	public static final String HD_Qty_Of_Nxt_LW_Lvl_Items = "//tr[19]/td[3]/div/div/div[2]/div/div";
	
	public static final String HD_Specific_Handling_Code_Transportation = "//tr[20]/td[3]/div/div/div[2]/div/div";
	

	
	//Product Description View SAP Attributes
	
	public static final String Product_Desc_View = "//div[contains(text(),'Product Description')]";
	
	public static final String Product_Sap_Name = "//td[3]/div/div/div[2]/div/div";
	
	public static final String Product_Gtin_Name = "//tr[4]/td[3]/div/div/div[2]/div/div";
	
	public static final String Product_Functional_Name = "//tr[5]/td[3]/div/div/div[2]/div/div";
	
	public static final String Product_Desc = "//tr[6]/td[3]/div/div/div[2]/div/div";
	
	
	public static final String Product_Short_Desc = "//tr[7]/td[3]/div/div/div[2]/div/div";
	
	public static final String Product_Sub_Brand = "//tr[8]/td[3]/div/div/div[2]/div/div";
	
	public static final String Product_Variant = "//tr[9]/td[3]/div/div/div[2]/div/div";
	
	
	
	//Time Constants
	public static final int SECOND_1000 = 1000 * 4;
	
	public static final int SECOND_2000 = 2000 * 3;
	
	public static final int SECOND_3000 = 3000 * 2;
	
	public static final int SECOND_4000 = 4000;
	
	public static final int SECOND_5000 = 5000;
	
	public static final int SECOND_6000 = 6000;
	
	public static final int SECOND_7000 = 7000;
	
	public static final int SECOND_8000 = 8000;
	public static final int SECOND_20000 = 30000;
	public static final int SECOND_15000 = 18000;
	
	//jayanta 
	
	//div/div/div/div/div/div/div/div/div/div/div/div[3]/button	
	////span[contains(text(),'Queries')]
	////div[2]/button
	
	//td[4]/div/div/div
	//td[5]/div/div/div
	public static final String TAB_QUERIES_LEFT_HAND_NAVIGATION ="//span[contains(text(),'Queries')]"; //Used in Test Case Id REG_FR_TC.105
	public static final String TAB_QUERIES_LEFT_HAND_NAVIGATION_FR ="//span[contains(text(),'Interrogations')]"; 
	public static final String DROP_DOWN_ITEM ="//div[@id='search-select-menu-%entity.Article.name']/span";//Used in Test Case Id REG_FR_TC.105
	
	public static final String LISTED_ITEM = "//div[2]/div/table/tbody/tr/td[2]/div";//Used in Test Case Id REG_FR_TC.105
	
	public static final String TAB_BB_SEARCH_TERM = "//div[2]/div/table/tbody/tr[5]/td[3]/div/div/div[2]/div/div"; // //Used in Test Case Id REG_FR_TC.105 	
	
	public static final String SearchKeyWorkList ="//div[2]/div/table/tbody/tr[4]/td[3]/div/div/div[2]/div/div";//Used in Test Case Id REG_FR_TC.105
	
	public static final String Number1SearchTerm ="div[2]/div/table/tbody/tr[6]/td[3]/div/div/div[2]/div/div";//Used in Test Case Id REG_FR_TC.105
	
	public static final String eSubBrand ="tr[8]/td[3]/div/div/div[2]/div/div";//Used in Test Case Id REG_FR_TC.105
	
	public static final String eVarient ="//tr[9]/td[3]/div/div/div[2]/div/div";//Used in Test Case Id REG_FR_TC.105
	
	public static final String eFunctionalname ="//tr[10]/td[3]/div/div/div[2]/div/div";//Used in Test Case Id REG_FR_TC.105
		
	public static final String TAB_TASK_BB_SPREADS_FPA_UK = "//tr[3]/td/div/div/div";
	
	public static final String TAB_BB_ENRICHMENT_SPREADS_FPA_ITEM = "//td[3]/div/div";
	
	public static final String TAB_GTIN = "//div[2]/div/table/tbody/tr/td[5]/div";
	
	public static final String TAB_BB_VIEW ="//div[contains(text(),'Brand Building View')]"; //Used in Test Case Id REG_FR_TC.105   
	
	public static final String TAB_ECOMM_VIEW = "//div[contains(text(),'eCommerce View')]";     //Used in Test Case Id REG_FR_TC.105
	
	public static final String BUTTON_SHOW_ALL_IN_ECOMM_TAB_VIEW = "//td[3]/div/span/span";
	
	public static final String ECOMM_VIEW_STATUS ="//div[2]/div/table/tbody/tr[11]/td[3]/div/div/div[2]/div/div";
	
	public static final String Retailer_Name ="//td[3]/div/input"; //This is the Retailer Name Combo Box XPath
	
	public static final String Retailer_Start_Availability_Date="//td[3]/div/div/div[2]/div/div"; ////This is the Retailer Start Availability Date Xpath
	
	public static final String Retailer_End_Availability_Date = "//tr[4]/td[3]/div/div/div[2]/div/div"; //////This is the Retailer End Availability Date Xpath
	
	public static final String PSO_Sub_Category = "//div[2]/div/table/tbody/tr[10]/td[3]/div/div/div[2]/div/div"; //This is the PSO Sub Category Xpath
	
	
	public static final String  Hazard_Statement_Description = "//div[2]/div/table/tbody/tr[3]/td[3]/div/div/div[2]/div/div"; ////Used in Test Case Id REG_FR_TC.105
	
	public static final String  Precaution_Statement_Description = "//div[2]/div/table/tbody/tr[4]/td[3]/div/div/div[2]/div/div"; ////Used in Test Case Id REG_FR_TC.105
	
	public static final String  Instruction_For_use  = "//div[2]/div/table/tbody/tr[5]/td[3]/div/div/div[2]/div/div"; ////Used in Test Case Id REG_FR_TC.105
	
	public static final String  BB_Target_Market_Drop_Down  = "//td[3]/div/input"; ////Used in Test Case Id REG_FR_TC.105
	
	public static final String  TR_FR  = "//tr[21]/td[5]/div"; ////Used in Test Case Id REG_FR_TC.105
	
	public static final String  TR_UK  = "//tr[24]/td[5]/div"; ////Used in Test Case Id REG_FR_TC.105
	
	public static final String  TR_US  = "//tr[25]/td[5]/div"; ////Used in Test Case Id REG_FR_TC.105
	
	public static final String  TR_NL  = "//tr[28]/td[5]/div"; ////Used in Test Case Id REG_FR_TC.105
	
	public static final String TAB_HEAD_VIEW ="//div[contains(text(),'Header')]";
	
	public static final String TAB_PREV_VIEW ="//div[contains(text(),'Preview')]";
	
	public static final String TAB_MEDIA_VIEW ="//div[contains(text(),'Media')]";
	
	public static final String TAB_MEDIA__REF_VIEW ="//div[contains(text(),'Media Reference')]";
	
	public static final String TAB_GEN_VIEW ="//div[contains(text(),'General')]";
	
	public static final String TAB_CLASS_VIEW ="//div[contains(text(),'Classification')]";
	
	public static final String TAB_KITS_COMPONENTS_VIEW ="//div[contains(text(),'Kits and Components')]";
	
	public static final String TAB_REFS_VIEW ="//div[contains(text(),'References')]";
	
	public static final String TAB_HPC_FR_VIEW ="//div[contains(text(),'HPC France View')]";
	
	public static final String TAB_MDM_OPS_VIEW ="//div[contains(text(),'MDM Ops View')]";
	
	public static final String TAB_CD_OPS_VIEW ="//div[contains(text(),'CD Ops View')]";
	
	public static final String TAB_QUALITY_VIEW ="//div[contains(text(),'Quality status')]";
	
	public static final String TAB_HAND_DEL_VIEW ="//div[contains(text(),'Handling and Delivery View')]";
	
	public static final String TAB_PACK_VIEW ="//div[contains(text(),'Packaging Information View')]";
	
	public static final String TAB_LOGISTICS_VIEW ="//div[contains(text(),'Logistics')]";
	
	public static final String TAB_HZD_INFO_VIEW ="//div[contains(text(),'Hazardous Information')]";
	
	public static final String TAB_ORD_INFO_VIEW ="//div[contains(text(),'Ordering Information')]";
	
	public static final String TAB_TAX_VIEW ="//div[contains(text(),'Tax')]";
	
	public static final String TAB_PRICE_VIEW ="//div[contains(text(),'Price')]";
	
	public static final String TAB_FOOD_BEV_VIEW ="//div[contains(text(),'Food and Beverage')]";
	
	public static final String TAB_NUTRITION_VIEW ="//div[contains(text(),'Nutrition / Serving Information')]";
	
	public static final String TAB_ALLERGEN_VIEW ="//div[contains(text(),'Allergen Information')]";
	
	public static final String TAB_HPC_VIEW ="//div[contains(text(),'HPC View')]";
	
	public static final String TAB_CHANGE_VIEW ="//div[contains(text(),'Change Information')]";
	
	public static final String TAB_PROD_DESC_VIEW ="//div[contains(text(),'Product Description')]";
	

	//Header Tab
	public static final String GTIN ="//tr[4]/td[3]/div/div/div[2]/div/div"; 			// -> GTIN
	public static final String MANUFACTURE_ITEM_NUMBER ="//tr[5]/td[3]/div/div/div[2]/div/div"; 		//-> Manufac Item No
	public static final String MATERIAL_NUMBER = "//tr[8]/td[3]/div/div/div[2]/div/div"; 		// -> Material Number
	public static final String EAN_UCC_CODE = "//div[2]/div/table/tbody/tr[2]/td[3]/div/div/div[2]/div/div";		// -> EAN UCC Code
	public static final String SAP_NAME = "//div[2]/div/table/tbody/tr[5]/td[3]/div/div/div[2]/div/div";		// -> Sap Name
	
	//Media Reference
	public static final String LHS = "//td[3]/div/div/div[2]/div/div"; // ->LHS
	public static final String RHS = "//tr[3]/td[3]/div/div/div[2]/div/div";  // ->RHS
	public static final String BACK_OF_PACK = "//tr[4]/td[3]/div/div/div[2]/div/div";  // ->Back Of Pack
	public static final String TOP_OF_PACK = "//tr[5]/td[3]/div/div/div[2]/div/div"; // -> Top Of Pack
	
	//General
	public static final String BRAND_NAME = "//tr[2]/td[3]/div/div/div[2]/div/div";// ->Brand Name
	public static final String NUMBER_OF_COMPLETE_LAYER = "//div[2]/div/input";// -> Number of complete layer
	public static final String NUMBER_OF_ITEM_IN_A_COMPLETE_LAYER = "//tr[10]/td[3]/div/div/div[2]/div/div"; //-> Number of Item in complete layer
	public static final String GLOBAL_ITEM_CLASS = "//tr[13]/td[3]/div/div/div[2]/div/div";// -> Global Item Classification

	
	//HPC France View
	public static final String IPCE_CODE = "//td[3]/div/div/div[2]/div/div"; // ->ICPE code
	public static final String IPCE_SUB_CODE = "//tr[3]/td[3]/div/div/div[2]/div/div";// -> ICPE SUB Code
	public static final String DANGEROUS_SUBSTANCE_QUANTITY = "//tr[4]/td[3]/div/div/div[2]/div/div";// -> Dangerous Substance Quantity
	public static final String STATE_OF_GOOD = "//tr[6]/td[3]/div/div/div[2]/div/div";// -> State of good
	
	//MDM Ops
	public static final String LAYER_HEIGHT = "//tr[4]/td[3]/div/div/div[2]/div/div"; // -> Layer Height
	public static final String LAYER_WIDTH = "//tr[6]/td[3]/div/div/div[2]/div/div";// -> Layer Width
	public static final String LAYER_LENGTH = "//tr[8]/td[3]/div/div/div[2]/div/div";// -> Layer Length
	public static final String LAYER_GROSS_WEIGHT = "//tr[10]/td[3]/div/div/div[2]/div/div";// -> Layer Gross Weight
	
	//CD Ops View
	public static final String PRIX = "//tr[3]/td[3]/div/div/div[2]/div/div";// -> Prix
	public static final String CD_OPS_STATUS_DESC = "//div[2]/div/table/tbody/tr[2]/td[3]/div/div/div[2]/div/div";// -> CD Ops status desc
	public static final String CD_OPS_MANAGER_COMMENTS = "//div[2]/div/table/tbody/tr[3]/td[3]/div/div/div[2]/div/div";// -> CD ops manager comments
	
	//Handaling and delivery
	public static final String ALTERNAME_ITEM_CLASS_CODE = "//td[3]/div/div/div[2]/div/div"; // -> Alternate Item Classification code
	public static final String IMPORT_CLASSIFICATION_TYPE = "//tr[3]/td[3]/div/div/div[2]/div/div";// ->Import Classification Type
	public static final String IMPORT_CLASSIFICATION_VALUE = "//tr[4]/td[3]/div/div/div[2]/div/div";// -> Import Classification Value
	public static final String STACKING_FACTOR  = "//tr[5]/td[3]/div/div/div[2]/div/div"; // -> Stacking Factor
	
	//Packaging information view
	public static final String HAS_DISPLAY_READY_PACKAGING = "//td[3]/div/div/div[2]/div/div"; // -> Has display ready packaging
	public static final String PACKAGE_MARKS_ENVIRONMENT = "//tr[5]/td[3]/div/div/div[2]/div/div";// -> Package Marks Environment
	public static final String PACKAGING_MATERIAL_CODE = "//div[2]/div/table/tbody/tr[3]/td[3]/div/div/div[2]/div/div"; // ->Packaging material code
	public static final String PACKAGING_MATERIAL_CODE_DESC = "//div[2]/div/table/tbody/tr[4]/td[3]/div/div/div[2]/div/div"; // ->Packaging material code desc
	
	//Logistics
	public static final String PALLET_CODE = "//div[2]/div/table/tbody/tr[2]/td[3]/div/div/div[2]/div/div";// ->Pallet Code
	public static final String PALLET_TERS_CODE = "//tr[3]/td[3]/div/div/div[2]/div/div";// -> Pallet ters code
	public static final String VAR = "//div[2]/div/table/tbody/tr[4]/td[3]/div/div/div[2]/div/div";
	public static final String HEIGHT = "//tr[4]/td[3]/div/div/div[2]/div/div"; // ->Height
	
	//Hazardous Information
	public static final String DANGEROUS_GOODS_TECHNICAL_NAME = "//td[3]/div/div/div[2]/div/div";// ->Dangerous Goods Technical name
	public static final String DANGEROUS_GOODS_SHIPPING_NAME = "//tr[4]/td[3]/div/div/div[2]/div/div";// ->Dangerous Goods Shipping Name
	public static final String REGULATED_PRODUCT_CODE = "//tr[5]/td[3]/div/div/div[2]/div/div";// -> regulated product code
	public static final String GHS_SIGNAL_CODE = "//tr[6]/td[3]/div/div/div[2]/div/div";// -> GHS Signal Code
	
	//Ordering Information
	public static final String IS_TRADE_ITEM_DISPLAY_UNIT = "//tr[7]/td[3]/div/div/div[2]/div/div";// -> Is trade item a display unit
	public static final String IS_INGREDIENT_IRRIDIATED = "//tr[8]/td[3]/div/div/div[2]/div/div";// -> Is ingredient irridiated
	public static final String INVOICE_NAME = "//div[2]/div/table/tbody/tr[3]/td[3]/div/div/div[2]/div/div";// -> Invoice name
	public static final String GENETICALLY_MODIFIED = "//tr[9]/td[3]/div/div/div[2]/div/div";// -> Genetically Modified
	
	//Tax
	public static final String TAX_RATE = "//td[3]/div/div/div[2]/div/div";// ->Tax rate
	public static final String TAX_RATE_TYPE_CODE = "//tr[3]/td[3]/div/div/div[2]/div/div";// -> Tax Rate Type Code
	public static final String TAX_RATE_TYPE_DESC = "//tr[4]/td[3]/div/div/div[2]/div/div";// -> Tax Rate Type Desc
	public static final String TAX_RATE_TYPE_AGENCY = "//tr[5]/td[3]/div/div/div[2]/div/div";// -> Tax Rate Type Agency
	
	//Food and Beverage
	public static final String IS_INGRED_IRRD = "//td[3]/div/div/div[2]/div/div"; // -> Is Ingredient Irridiated
	public static final String HANDLING_INSTRUCTION_CODE = "//tr[3]/td[3]/div/div/div[2]/div/div";// -> Handling Instruction Code
	public static final String PRESERVATION_TECHNIQUE_CODE = "//tr[4]/td[3]/div/div/div[2]/div/div";// -> Preservation Technique Code
	public static final String NUMBER_OF_SERVING_PER_PACKAGE = "//tr[6]/td[3]/div/div/div[2]/div/div";// -> Number Of Serving Per Package
	
	//HPC View
	public static final String NON_FOOD_INGREDIENT_STATEMENT = "//td[3]/div/div/div[2]/div/div";// ->Non Food Ingredient Statement
	public static final String HPC_USAGE_INSTRUCTION = "//tr[3]/td[3]/div/div/div[2]/div/div";// - > HPC Usage Instruction
	public static final String HPC_STORAGE_INSTRUCTION = "//tr[4]/td[3]/div/div/div[2]/div/div";// -> Hpc Storage Instruction
	
	//Change Information
	public static final String PRODUCT_DESCRIPTION = "//tr[6]/td[3]/div/div/div[2]/div/div";// -> Product Desc
	public static final String SHORT_DESCRIPTION = "//tr[7]/td[3]/div/div/div[2]/div/div";// -> Short Desc
	public static final String SUB_BRAND = "//tr[8]/td[3]/div/div/div[2]/div/div";// -> Sub Brand
	public static final String VARIENT = "//tr[9]/td[3]/div/div/div[2]/div/div";// -> Varient
	
	
	public static final String ARROW_KEY_NEXT = "//button[2]";//
	public static final String DATE_OF_CD_OPS_STATUS = "//td[3]/div/div/div[2]/div/div"; 
	public static final String CD_OPS_STATUS_DESCRIPTION = "//div[2]/div/table/tbody/tr[2]/td[3]/div/div/div[2]/div/div";
	public static final String CD_OPS_ENRICHMENT_COMPLETE="//tr[4]/td[3]/div/div/div[2]/div/div";
	public static final String PRIX_MARKETING_RECOMENDED ="//tr[3]/td[3]/div/div/div[2]/div/div";
	
	public static final String RADIO_BUTTON_CREATE_SELECTION_AS_NEW ="//span[2]/input";
	
	public static final String RADIO_BUTTON_ASSIGNED_USER_DROPDOWN ="//td/span/input";//
	
	public static final String DROPDOWN_ASSIGNED_USER_DROPDOWN ="//td[2]/div/input"; //
	
	public static final String DROPDOWN_RESPONSIBLE_USER_DROPDOWN ="//tr[8]/td[3]/div/input";
	
	public static final String OK_BUTTON ="(//span[contains(text(),'OK')])[1]";
	
	public static final String FIRST_ITEM_MY_TASK ="//td[4]/div/div";// 
	
	public static final String TASK_ITEM ="//td[3]/input";
	
	public static final String VIEW ="//div[3]/div/span[3]";
	
	public static final String VIEW_SHORT ="//div[2]/div/div/span[2]/span";
	
	public static final String TAB_eCOMMERCE_FRANCE_TASK_NOT_ACCEPTED = "//div[contains(text(),'eCommerce-France')]";
	
	public static final String eCommerce_France_Item1 ="//td[3]/div/div";
	
	public static final String eCommerce_France_Item2 ="//tr[2]/td[3]/div/div";
	
	public static final String eCommerce_France_Item3 ="//tr[3]/td[3]/div/div";
	
	public static final String eCommerce_France_Item4 ="//tr[4]/td[3]/div/div";
	
	public static final String eCommerce_France_Item5 ="//tr[5]/td[3]/div/div";
	
	public static final String eCommerce_France_Item6 ="//tr[6]/td[3]/div/div";
	
	public static final String eCommerce_France_Item7 ="//tr[7]/td[3]/div/div"; //
	
	public static final String eCommerce_France_Is_eComm_Product ="//div[2]/div/table/tbody/tr[10]/td[3]/div/div/div[2]/div/div";
	
	public static final String REFRESH_BUTTON="//div[3]/div/span[2]";
	
	public static final String FILTER_BUTTON="//div[3]/div/span"; //
	
	public static final String DROPDOWN_BUTTON="//input"; //
	
	public static final String FILTER_SEARCH_TEXTBOX="//div[5]/input"; //
	
	public static final String FILTER_BUTTON_SUBMIT="//div[7]/div/span";
	
	public static final String eComm_Enrichment_Complete="//div[2]/div/table/tbody/tr[15]/td[3]/div/div/div[2]/div/div";
	
	public static final String eComm_PSO_SUB_CAT="//div[2]/div/table/tbody/tr[9]/td[3]/div/div/div[2]/div/div";
	
	public static final String QUALITY_STATUS_1_VOT_IMAGE =".//*[@id='detail_quality_status']/div[2]/div[1]/table/tbody/tr[1]/td[2]/div";
		
	public static final String QUALITY_STATUS_1_VOT_TITLE_NULL_CHECK =".//*[@id='detail_quality_status']/div[2]/div[1]/table/tbody/tr[2]/td[2]/div";// 
	
	
	public static final String QUALITY_STATUS_ALLERGEN_INFO ="//div[@id='detail_quality_status']/div[2]/div/table/tbody/tr[3]/td[2]/div/div";
	
	
	public static final String QUALITY_STATUS_BRAND="//div[@id='detail_quality_status']/div[2]/div/table/tbody/tr[4]/td[2]/div/div";
	
	public static final String QUALITY_STATUS_BRAND_NAME="//div[@id='detail_quality_status']/div[2]/div/table/tbody/tr[6]/td[2]/div/div";
	
	public static final String QUALITY_STATUS_CATEGORY ="//div[@id='detail_quality_status']/div[2]/div/table/tbody/tr[7]/td[2]/div/div";
	
	public static final String QUALITY_STATUS_DROPDOWN ="//div[3]/div/div/div/input";  //div[@id='dataquality_channel_combobox']/input
	
	public static final String BB_NET_CONTENT1 = "//div[2]/div/table/tbody/tr[8]/td[3]/div/div/div[2]/div/div";
	
	public static final String BB_NET_CONTENT_UOM1 = "//div[2]/div/table/tbody/tr[9]/td[3]/div/div/div[2]/div/div";
	
	public static final String QUALITY_ONE_VOT_NAME_AND_TITLE = "//div[@id='detail_quality_status']/div[2]/div/table/tbody/tr/td[2]/div/div";
	
	public static final String QUALITY_ONE_VOT_NAME_AVAILABLE = "//div[@id='detail_quality_status']/div[2]/div/table/tbody/tr[3]/td[2]/div/div";
	
	public static final String QUALITY_LONG_FORM_PRODUCT_DESC = "//div[@id='detail_quality_status']/div[2]/div/table/tbody/tr[13]/td[2]/div/div";
	
	public static final String QUALITY_SERACH_TERM = "//div[@id='detail_quality_status']/div[2]/div/table/tbody/tr[15]/td[2]/div/div";
	
	public static final String QUALITY_NUTRIENT_AVAILABLE = "//div[@id='detail_quality_status']/div[2]/div/table/tbody/tr[16]/td[2]/div/div";
	
	public static final String QUALITY_STATUS_DROPDOWN1 ="//div[@id='dataquality_channel_combobox']/input";  
	
	public static final String ERROR_POPUP_CANCEL ="//div[@id='pimwebaccess-1169917604-overlays']/div[3]/div/div/div[3]/div/div/div/div[2]/div/div/div[5]/div/span/span";  
	
	public static final String QUALITY_STATUS_E_FUNCTIONAL_NAME ="//div[@id='detail_quality_status']/div[2]/div/table/tbody/tr[20]/td[2]/div/div";//
	
	public static final String QUALITY_STATUS_E_SIZE ="//div[@id='detail_quality_status']/div[2]/div/table/tbody/tr[21]/td[2]/div/div";
	
	public static final String QUALITY_STATUS_E_VARIENT ="//div[@id='detail_quality_status']/div[2]/div/table/tbody/tr[23]/td[2]/div/div";
	
	public static final String QUALITY_STATUS_E_SUB_BRAND ="//div[@id='detail_quality_status']/div[2]/div/table/tbody/tr[22]/td[2]/div/div";//
	
	public static final String QUALITY_STATUS_1Vot_India_user ="//div[@id='detail_quality_status']/div[2]/div/table/tbody/tr[14]/td[2]/div/div";
	
	public static final String QUALITY_STATUS_1Vot_India_length_user ="//div[@id='detail_quality_status']/div[2]/div/table/tbody/tr[13]/td[2]/div/div";
	
	public static final String QUALITY_STATUS_1Vot_India_title_similarity ="//div[@id='detail_quality_status']/div[2]/div/table/tbody/tr[12]/td[2]/div/div";
	
	public static final String VALIDATION_RULE ="//div[@id='search']/div/div[2]/div/div/div/div/div[2]/div/div[2]/div/div/div[7]/div/div[2]/input";
	
	//below variable are used for quality tabs for Local DQ Rule for INDIA BB user
	
	public static final String ASIN_CODES_AVAILABLE ="//div[@id='detail_quality_status']/div[2]/div/table/tbody/tr/td[2]/div/div";
	public static final String BASE_PACK_WPC ="//div[@id='detail_quality_status']/div[2]/div/table/tbody/tr[4]/td[2]/div/div";
	public static final String BASE_PACK_DESC ="//div[@id='detail_quality_status']/div[2]/div/table/tbody/tr[5]/td[2]/div/div";
	public static final String BASE_PACK_CODE_AVAIL ="//div[@id='detail_quality_status']/div[2]/div/table/tbody/tr[6]/td[2]/div";
	public static final String ONE_VOT_NAME_AVAILL ="//div[@id='detail_quality_status']/div[2]/div/table/tbody/tr[24]/td[2]/div/div";
	
	//below variable are used for quality tabs for Global DQ Rule for INDIA BB user
	public static final String eSize ="//div[@id='detail_quality_status']/div[2]/div/table/tbody/tr[20]/td[2]/div/div";
	public static final String eSubbrand ="//div[@id='detail_quality_status']/div[2]/div/table/tbody/tr[21]/td[2]/div/div";
	
	
	public static final String LISTED_BASE_PACK ="//tr[2]/td[9]/div";
	
	public static final String LOGISTICS_GROSS_WEIGHT_PATH ="//tr[10]/td[3]/div/div/div[2]/div/div";
	
	public static final String LOGISTICS_NET_WEIGHT_PATH ="//tr[12]/td[3]/div/div/div[2]/div/div";
	
	public static final String LOGISTICS_VOLUME_PATH ="//tr[14]/td[3]/div/div/div[2]/div/div";
	
	//Index Search
	public static final String SRCH_PROD_COUNT1 = "//div[1]/div/div/div[2]/div/div/div/div/div[2]/button";
	public static final String EIGHT_PRODUCT_TASK = "//div[2]/div/table/tbody/tr[8]/td[5]/div";
	public static final String indx_EANUCC_code_PATH ="//span[contains(text(),'EAN UCC Code')]";
	public static final String indx_RPC_code_PATH ="//span[contains(text(),'Retailer Product Code')]";
	public static final String indx_PSO_Category_PATH ="//span[contains(text(),'PSO Sub Category')]";
	public static final String indx_Category_PATH ="//span[starts-with(text(),'Category')]";
	public static final String indx_eFunction_Name_PATH ="//span[contains(text(),'eFunctional Name')]";
	public static final String indx_eSubbrand_PATH ="//span[contains(text(),'eSubbrand')]";
	public static final String indx_eVariant_PATH ="//span[contains(text(),'eVariant')]";
	public static final String indx_1Vot_Ecommerce_PATH ="//span[contains(text(),'1VOT eCommerce Title')]";
	public static final String indx_SAPName_PATH ="//span[contains(text(),'SAP Name')]";
	public static final String indx_BrndNme_PATH ="//span[contains(text(),'Brand Name')]";
	public static final String indx_product_typ_PATH ="//span[contains(text(),'Product Type')]";
	public static final String indx_trgt_mkt_PATH ="//span[contains(text(),'Target Market')]";
	public static final String indx_IPgln_PATH ="//span[contains(text(),'IPGLN')]";
	public static final String indx_mrtl_nmbr_PATH ="//span[contains(text(),'Material Number')]";
	
	
}