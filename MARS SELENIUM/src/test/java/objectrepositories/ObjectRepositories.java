package objectrepositories;

// import org.openqa.selenium.By;

public class ObjectRepositories
{

	//updateSAPdataManually
	//updateBBViewAttributesData
	//navigateToBrandBuildingView
	//  ##### Login Page Objects #####
	
	public static final String IDD_UI_TXT_LOGIN_USER_ID = "(//input[@class='infaField'])[1]";   
    public static final String IDD_UI_TXT_LOGIN_PWD = "(//input[@class='infaField'])[2]";   
    public static final String IDD_UI_BTN_LOGIN = "//*[text()='Log In']";
    public static final String IDD_UI_CreateinDataView = "//*[text()='Create in Data View']";
    public static final String IDD_UI_ClickOn_Data = "//span[text()='Data']";
    public static final String IDD_UI_COMPANY_NAME="//*[text()='COMPANY NAME']/following-sibling::td//tbody//tr//td//input";
    public static final String IDD_UI_Apply = "//*[text()='Apply']";
    public static final String IDD_UI_Save = "//*[text()='Save']']";
    
    public static final String IDD_UI_Queries ="//*[text()='Queries']";
    public static final String IDD_UI_OpenQueries="(//*[text()='Open Query'])[2]";
    
    public static final String IDD_UI_SearchForm_AccountNumber="//*[text()='Account Number ']";
    public static final String IDD_UI_SearchForm_Account_with_Details="//*[text()='Account with Details']";
    public static final String IDD_UI_SearchForm_CommercialAct_SearchByAddress="(//*[text()='Search By Address'])[1]";
    
    public static final String IDD_UI_ACCOUNT_NUMBER_TextField_Under_Search ="//*[@class='searchTextField']";
    public static final String IDD_UI_RunSearch ="//*[text()='Run Search']";
    public static final String IDD_UI_Open ="(//*[text()='Open'])[1]";
    
    public static final String IDD_UI_AccountNumber_From_DataTab ="//*[text()='ACCOUNT NUMBER']/following-sibling::td[1]";
    public static final String IDD_UI_ACCOUNT_DESCRIPTION_From_DataTab ="//*[text()='ACCOUNT DESCRIPTION']/following-sibling::td[1]";
    
    public static final String IDD_UI_COMPANY_LEGAL_NAME = "//*[text()='COMPANY LEGAL NAME']/following-sibling::td//tbody//tr//td//input";
    public static final String IDD_UI_BUSINESS_TYPE="//*[text()='BUSINESS TYPE']/following-sibling::td//div//table//tbody//tr//td//div//div";

	
	//	-------------------------------------------------------------------------
	public static final String TXT_LOGIN_USER_ID = "//input[@id='userName']";   
	public static final String TXT_LOGIN_PWD = "//input[@id='password']";   
	public static final String BTN_LOGIN = "//input[@id='login-button']";
	
	//public static final By TXT_USERID = By.id("userName");
	
	public static final String BTN_LOGOUT = "//button[@id='link_logout']";
 
	public static final String TAB_TASKS = "//span[contains(text(),'Tasks')]";
	public static final String TAB_QUERIES = "//span[contains(text(),'Queries')]";
	//public static final String TAB_QUERIES = ".//*[@id='view.container.context.iconstrip']/div/div/div[1]/div/div/div/div/div/div/div/div[2]/button";
	//public static final String TAB_SEARCH = "//span[contains(text(),'Search')]";
	public static final String TAB_SEARCH = "//span[contains(text(),'Search')]";
	public static final String TAB_DASHBOARD = "//span[contains(text(),'Dashboard')]";
	
	public static final String DIV_TABLE_VIEW_SEARCH = "//div[contains(text(),'Table view')]";
	
	public static final String TASK_ECOMM_TEAM_MANAGER = "//div[contains(text(),'eCommerce Team Manager - China')]";
	
	public static final String FIRST_TASK = "//td[3]/div/div";
	
	public static final String FIRST_PRODUCT_TASK = "//div[2]/div/table/tbody/tr/td[5]/div";
	
	
	
	public static final String BTN_FULLTEXT_SEARCH = "//div[@id='fulltextsearch_button']/span";
	
	public static final String TXT_FULLTEXT_SEARCH = "search_input_textfield";
	
	public static final String SRCH_PROD_COUNT = "//button[@id='button_back']/span";
	public static final String SRCH_PROD_COUNTSIT = "//div[2]/div/div/div/div/div/button";
	public static final String lan_Sel =".//*[@id='language']";
	
	public static final String VER_ATR_CHI = ".//*[@id='MainLayout']/div/div[2]/div/div/div[3]/div/div[2]/div/div/div[1]/div/div/div/div";
	
    public static final String QRY_CLK = ".//*[@id='view.container.context.iconstrip']/div/div/div[1]/div/div/div/div/div/div/div/div[2]/button";
    
    public static final String ITM_DRPDWN = ".//*[@id='search-select-menu-%entity.Article.name']/span";
	public static final String ITM_SELC = "//span[text()='Items in a catalog']";
	
	
	public static final String ITM_SELC_FRENCH = "//span[text()='Articles d'un catalogue']";
	
	
	public static final String RNDOMELEM ="//tr[2]/td[3]/div/input";
	
	
	public static final String SRCH_BTN = "//div[9]/div/span/span";
	
	public static final String PRDCT_CLK = ".//*[@id='article_table']/div[2]/div[1]/table/tbody/tr[1]/td[5]/div";
	
	public static final String GEN_VIEW = "//td[5]/div/div/div";
	
	public static final String PRD_TLTIP = "//tr[7]/td[3]/div/div/div[2]/div/div";
	
	
	public static final String BB_VIEW = "//div[contains(text(),'Brand Building View')]";
	public static final String PACKAGING_INFORMATION_VIEW = "//div[contains(text(),'Packaging Information View')]";
	
	public static final String BB_VIEW_FRENCH = "//div[contains(text(),'Vue Brand Building')]";
	
	public static final String Header = "//div[contains(text(),'Header')]";
	public static final String GENERAL_TAB = "//div[contains(text(),'General')]";
	public static final String HAZARDOUS_INFO_TAB = "//div[contains(text(),'Hazardous Information')]";
	
	
	
	public static final String GTIN="//td[3]/div/div/div[2]/div/div";
	
	public static final String MEDIA_REF = "//div[contains(text(),'Media Reference')]";
	
	public static final String close = "//span[contains(text(),'Close')]";
	
	
	
	public static final String Target_Market = "//tr[2]/td[3]/div/div";
	public static final String Target_Market1 = "//div[2]/div/div/div/div/div/div/div/div/table/tbody/tr/td[3]/div/div";
	
	public static final String SLCT_CHINA = "//span[text()='China']";
	
	public static final String DRPDOWN = "//tr[2]/td[3]/div/div";
	
	public static final String Ecom_VIEW = "//div[contains(text(),'eCommerce View ')]";
	
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
	
	public static final String CREATE_TASK_XPATH = ".//*[@id='pimwebaccess-1169917604-overlays']/div[2]/div/div/span[8]/span";
	
	public static final String TASK_NAME_XPATH = "//*[@id='Task.Name']";
	
	public static final String TASK_DESC_XPATH = "//*[@id='Task.Description']";
	
	public static final String TASK_USER_XPATH = "//*[@id='Task.User']/div";

	public static final String TASK_USER_INPUT_XPATH = ".//*[@id='Task.User']/input";
	
	public static final String TASK_USER_INPUT_DROP_XPATH = ".//*[@id='VAADIN_COMBOBOX_OPTIONLIST']/div/div[2]/table/tbody/tr/td";
	
	public static final String TASK_USER_INPUT_DROP_XPATH_2 = ".//*[@id='VAADIN_COMBOBOX_OPTIONLIST']/div/div[2]/table/tbody/tr[1]/td";
	
	public static final String TASK_RESPOSIBLE_INPUT_XPATH = "//div[@id='Task.Responsible']/input";
	
	public static final String CREATE_TASK_OK_XPATH = "//span[contains(text(),'OK')]";
	
	public static final String TASK_LIST_XPATH = "//*[@id='task_table']/div[2]/div[1]/table/tbody/tr";
	
	public static final String MARK_AS_COMPLETED_XPATH = "//span[contains(text(),'Mark as completed')]";
	
	public static final String ITEM_LIST_XPATH = "//*[@id='pimwebaccess-1169917604-overlays']/div[2]/div/div/span";
	
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
	
	public static final String ONEVOT_ECOMM_TITLE="//tr[11]/td[3]/div/div/div[2]/div/div"; 
	
	public static final String SAP_NAME = "//tr[7]/td[3]/div/div/div[2]/div/div";
	
	public static final String show_ALL_BB_VIEW ="//td[3]/div/span/span";
	
	public static final String Lang_Specific_Brand_name = "//tr[2]/td[3]/div/div/div[2]/div/div";
	
	public static final String e_SUBBRAND ="//tr[9]/td[3]/div/div/div[2]/div/div";
	
	public static final String BB_E_VAR ="//tr[9]/td[3]/div/div/div[2]/div/div";
	public static final String SUBBRAND ="//tr[4]/td[3]/div/div/div[2]/div/div";
	
	public static final String e_FunctionalName = "//tr[11]/td[3]/div/div/div[2]/div/div";
	
	
	public static final String FunctionalName = "//tr[5]/td[3]/div/div/div[2]/div/div";
	
	
	public static final String e_Variant = "//tr[10]/td[3]/div/div/div[2]/div/div";
	
	public static final String E_SUBBRAND_SIT_USER_BB_VIEW = "//tr[10]/td[3]/div/div/div[2]/div/div";
	
	public static final String E_FUNCT_NAME = "//tr[10]/td[3]/div/div/div[2]/div/div";
	
	public static final String Variant ="//tr[6]/td[3]/div/div/div[2]/div/div";
	
	
	public static final String UOM_DROPDOWN="//div[2]/div/table/tbody/tr[8]/td[3]/div/div";
	
	
	public static final String LONG_FORM_PROD_DESCRIPTION_SIT_USER_BB_VIEW="//tr[28]/td[3]/div/div/div[2]/div/div";
	
	public static final String METRIC="//tr[2]/td/span";
	
	
	public static final String NET_CONTENT="//div[2]/div/table/tbody/tr[18]/td[3]/div/div/div[2]/div/div";
	
	public static final String NET_CONTENT_UOM="//div[2]/div/table/tbody/tr[19]/td[3]/div/div/div[2]/div/div";
	
	
	
	public static final String e_size = "//div[2]/div/table/tbody/tr[3]/td[3]/div/div/div[2]/div/div";
	
	public static final String ITEM_NO = "//div[2]/div/div[2]/div/div[2]/div/div";
	
	public static final String REFRESH ="//div[3]/div/span[2]";
	
	public static final String PSO_SUB_CATEGORY = "//div[2]/div/div/div/div/div/div/div/div/table/tbody/tr/td[3]/div/div";
	
	public static final String PSO_SUB_CATEGORY_LABEL = "//div[2]/div/table/tbody/tr[10]/td/div/span";
	
	public static final String PSO_SUB_CATEGORY_DRP_DWN = ".//*[@id='detail_edit_d340c099fc33ecacfa45b457dbe1d5e2']/div/div[2]/div/div/div";
	
	public static final String PSO_SUB_CATEGORY_DRP_DWN_LIST = ".//*[@id='VAADIN_COMBOBOX_OPTIONLIST']/div/div[2]/table/tbody/tr";

	
	
	// India xpath**************************************************************************************************
	
	public static final String SHOW_ALL_BUTTON ="//td[3]/div/span/span";

	public static final String FEATURE_BENEFIT_1 ="//tr[17]/td[3]/div/div/div[2]/div/div";
	
	
public static final String BB_SAP_NAME = "//tr[10]/td[3]/div/div/div[2]/div/div";
	
	public static final String BB_1VOT_ECOMMERCE_TITLE = "//tr[14]/td[3]/div/div/div[2]/div/div";
	
	public static final String BB_BRAND = "//tr[2]/td[3]/div/div/div[2]/div/div";
	
	public static final String BB_SUB_BRAND = "//tr[7]/td[3]/div/div/div[2]/div/div";
	
	public static final String BB_FUNCTIONAL_NAME = "//tr[8]/td[3]/div/div/div[2]/div/div";
	
	public static final String BB_E_SUBBRAND = "//tr[8]/td[3]/div/div/div[2]/div/div";
	
	public static final String BB_VARIANT = "//tr[9]/td[3]/div/div/div[2]/div/div";
	
	public static final String BB_NET_CONTENT = "";
	
	public static final String BB_NET_CONTENT_UOM = "";
	
	public static final String BB_ESUB_BRAND = "//tr[11]/td[3]/div/div/div[2]/div/div";
	
	public static final String E_VARIANT_SIT_USER_BB_VIEW = "//tr[11]/td[3]/div/div/div[2]/div/div";
	
	public static final String BB_EFUNCTIONAL_NAME = "//tr[13]/td[3]/div/div/div[2]/div/div";
	
	public static final String BB_EVARIANT = "//tr[12]/td[3]/div/div/div[2]/div/div";
	public static final String E_FUC_NAME_SIT_USER_BB_VIEW = "//tr[12]/td[3]/div/div/div[2]/div/div";
	public static final String BB_ESIZE = "//div[2]/div/table/tbody/tr[3]/td[3]/div/div/div[2]/div/div";

	public static final String TAB_MY_TASK = "//div[contains(text(),'My tasks')]";
	public static final String TAB_TASK_NOT_ACCEPTED = "//div[contains(text(),'Tasks not accepted')]";
	public static final String TAB_MY_RESPONSIBILITY = "//div[contains(text(),'My responsibilities')]"; 
	
	
	
	
	public static final String ORIGIN_CITY_BB_VIEW="//tr[24]/td[3]/div/div/div[2]/div/div";
	public static final String ESIZE_SIT_USER_BB_VIEW = "//div[2]/div/table/tbody/tr[3]/td[3]/div/div/div[2]/div/div";
	
	public static final String SEARCH_KEYWORD_SIT_USER_BB_VIEW ="//div[2]/div/table/tbody/tr[7]/td[3]/div/div/div[2]/div/div";
	public static final String FEATURESONE_SIT_USER_BB_VIEW="//tr[15]/td[3]/div/div/div[2]/div/div";
	

	public static final String NAME_LABEL_FOR_TASK = "//span[contains(text(),'Name:')]";
	public static final String SAPNAME_BB_VIEW="//tr[10]/td[3]/div/div/div[2]/div/div";
	
	public static final String LANG_SPEC_BRND_NME_BB_VIEW="//tr[6]/td[3]/div/div/div[2]/div/div";
	
	public static final String IS_DANG_SUBSTNCE ="//tr[24]/td[3]/div/div/div[2]/div/div";
	
	public static final String WARNING = "//tr[29]/td[3]/div/div/div[2]/div/div";
	
	public static final String GTIN_NAME_HEADER = "//td[3]/div/div/div[2]/div/div";
	
	public static final String EAN_UU_TYPE_HEADER ="//div[2]/div/table/tbody/tr/td[3]/div/div/div[2]/div/div";
	public static final String EAN_UU_CODE_HEADER="//div[2]/div/table/tbody/tr[2]/td[3]/div/div/div[2]/div/div";
	public static final String ITEM_NO_HEADER="//tr[3]/td[3]/div/div/div[2]/div/div";
	public static final String GTIN_HEADER="//tr[4]/td[3]/div/div/div[2]/div/div";
	public static final String MANUFC_ITEM_NO="//tr[5]/td[3]/div/div/div[2]/div/div";
	public static final String MANUFACTURE_HEADER="//tr[6]/td[3]/div/div/div[2]/div/div";
	public static final String IPGLN_HEADER="//tr[7]/td[3]/div/div/div[2]/div/div";
	public static final String MATERIALNO_HEADER="//tr[8]/td[3]/div/div/div[2]/div/div";
	public static final String PROD_TYPE_HEADER="//tr[9]/td[3]/div/div/div[2]/div/div";
	public static final String SAP_NAME_HEADER="//div[2]/div/table/tbody/tr[5]/td[3]/div/div/div[2]/div/div";
	
	public static final String PROD_TYPE_GENERAL="//td[3]/div/div/div[2]/div/div";
	public static final String BRAND_NAME_GENERAL="//tr[2]/td[3]/div/div/div[2]/div/div";
	public static final String BRAND_OWNER_GENERAL="//tr[3]/td[3]/div/div/div[2]/div/div";
	public static final String IS_SERVICE_GENERAL="//tr[4]/td[3]/div/div/div[2]/div/div";
	public static final String IS_CONSUMER_UNIT_GENERAL="//td[3]/div/span";
	public static final String IS_BASE_UNIT_GENERAL="//tr[6]/td[3]/div/span";
	public static final String QUANTITY_LOWER_ITEMS_GENERAL="//tr[8]/td[3]/div/div/div[2]/div/div";
	public static final String Number_of_complete_layers_contained_in_item_GENERAL="//tr[9]/td[3]/div/div/div[2]/div/div";
	public static final String ITEMS_IN_COMPLETE_LAYER_GENERAL="//tr[10]/td[3]/div/div/div[2]/div/div";
	public static final String Is_Packaging_Market_Returnable_GENERAL="//tr[5]/td[3]/div/div/div[2]/div/div";
	public static final String START_AVAI_DATE_GENERAL="//tr[6]/td[3]/div/div/div[2]/div/div";
	public static final String END_AVAI_DATE_GENERAL="//tr[7]/td[3]/div/div/div[2]/div/div";
	public static final String COUN_ORIGIN_GENERAL="//div[2]/div/table/tbody/tr[8]/td[3]/div/div/div[2]/div/div";
	public static final String UNITS_PER_CASE_GENERAL="//div[2]/div/table/tbody/tr[9]/td[3]/div/div/div[2]/div/div";
	public static final String IS_PRIVATE_GENERAL="//tr[10]/td[3]/div/span";
	public static final String LAYERS_PER_PALLET_GENERAL="//div[2]/div/table/tbody/tr[11]/td[3]/div/div/div[2]/div/div";
	public static final String ITEMS_PER_PALLET_LAYER_GENERAL="//div[2]/div/table/tbody/tr[12]/td[3]/div/div/div[2]/div/div";
	public static final String ITEMS_PER_PALLET_GENERAL="//div[2]/div/table/tbody/tr[13]/td[3]/div/div/div[2]/div/div";
	public static final String PROD_RECYCLABLE_GENERAL="//div[2]/div/table/tbody/tr[14]/td[3]/div/div/div[2]/div/div";
	public static final String MANUFACTURER_GENERAL="//tr[15]/td[3]/div/div/div[2]/div/div";
	public static final String FUCNTIONAL_NAME_GENERAL="//tr[17]/td[3]/div/div/div[2]/div/div";
	public static final String SHORT_DESC_GENERAL="//div[2]/div/table/tbody/tr[18]/td[3]/div/div/div[2]/div/div";
	public static final String ADDITIONAL_DESC_GENERAL="//div[2]/div/table/tbody/tr[19]/td[3]/div/div/div[2]/div/div";
	public static final String VARIANT_GENERAL="//tr[21]/td[3]/div/div/div[2]/div/div";
	public static final String PROD_DESC_GENERAL="//tr[22]/td[3]/div/div/div[2]/div/div";
	public static final String SUBBRAND_GENERAL="//tr[23]/td[3]/div/div/div[2]/div/div";
	
	public static final String HAS_DISP_READY_PACK_PIV="//td[3]/div/div/div[2]/div/div";
	public static final String PACK_MARK_RETURN_PIV="//tr[3]/td[3]/div/div/div[2]/div/div";
	public static final String HAS_BATCH_NO_PIV="//tr[4]/td[3]/div/div/div[2]/div/div";
	public static final String PACK_MARK_ENVIR_PIV="//tr[5]/td[3]/div/div/div[2]/div/div";
	public static final String PACK_MARK_EXPIRA_DATE_TYPE_PIV="//tr[6]/td[3]/div/div/div[2]/div/div";
	public static final String PACK_MATE_COMP_UOM_PIV="//tr[7]/td[3]/div/div/div[2]/div/div";
	public static final String IS_NET_CONT_DEC_PIV="//tr[8]/td[3]/div/div/div[2]/div/div";
	public static final String PACK_MATERI_CODE_PIV="//div[2]/div/table/tbody/tr[3]/td[3]/div/div/div[2]/div/div";
	public static final String PACK_MATERI_CODE_DESC_PIV="//div[2]/div/table/tbody/tr[4]/td[3]/div/div/div[2]/div/div";
	
	public static final String DANG_GOODS_TECH_NAME_HAZ_INFO="//td[3]/div/div/div[2]/div/div";
	public static final String DANG_GOODS_SHIPP_NAME_HAZ_INFO="//tr[4]/td[3]/div/div/div[2]/div/div";
	public static final String DANG_GOODS_MARGIN_NO_HAZ_INFO="//tr[2]/td[3]/div/div/div[2]/div/div";
	public static final String DANG_GOODS_CLASS_CODE_HAZ_INFO="//div[2]/div/table/tbody/tr[3]/td[3]/div/div/div[2]/div/div";
	public static final String DANG_GOODS_HAZ_CODE_HAZ_INFO="//div[2]/div/table/tbody/tr[4]/td[3]/div/div/div[2]/div/div";
	public static final String DANG_GOODS_INDICATOR_HAZ_INFO="//div[2]/div/table/tbody/tr[5]/td[3]/div/div/div[2]/div/div";
	public static final String DANG_GOODS_PACKA_GROUP_HAZ_INFO="//tr[6]/td[3]/div/div/div[2]/div/div";
	public static final String DANG_GOODS_REGU_CODE_HAZ_INFO="//tr[7]/td[3]/div/div/div[2]/div/div";
	public static final String DANG_GOODS_IS_DANG_SUBST_HAZ_INFO="//tr[8]/td[3]/div/div/div[2]/div/div";
	public static final String DANG_GOODS_FLASHPOINT_TEMP_HAZ_INFO="//tr[12]/td[3]/div/div/div[2]/div/div";
	public static final String DANG_GOODS_FLASHPOINT_TEMP_UOM_HAZ_INFO="//tr[13]/td[3]/div/div/div[2]/div/div";
	
	
	
}