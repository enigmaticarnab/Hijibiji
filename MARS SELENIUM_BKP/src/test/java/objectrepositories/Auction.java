package objectrepositories;

public class Auction {
	
//  ##### Login Page Objects #####
	
	public static final String TXT_LOGIN_USER_ID = "//*[@id='textfield-1420-inputEl']";  
	public static final String TXT_LOGIN_PWD = "//*[@id='textfield-1422-inputEl']";  
	public static final String BTN_LOGIN = "//*[text()='Login']"; 
	public static final String BTN_ROLE_EDIT = "//*[@name='combobox-1424-inputEl']" ;
	public static final String TXT_ROLE = "//div[text()='Role']";
	public static final String TXT_ActionDashboard = "//span[text()='Auction Dashboard']";
	public static final String BTN_ROLE_DRPDWN ="(//*[@class='x-trigger-cell'])[1]" ;
	public static final String BTN_ROLE_SLT ="(//ul//li[text()='Group Registration Management V4'])";
	public static final String BTN_COMPANY ="(//*[@class='x-trigger-cell'])[2]" ;
	public static final String BTN_DSP = "//*[@id='button-1037-btnInnerEl']";	
	public static final String BTN_COMPANY_SLT ="//ul//li[text()='MANHEIM ATLANTA']";
	public static final String BTN_COMPANY_SLT_QA1 ="//ul//li[text()='MANHEIM OVC QA1']";	
	public static final String BTN_LOGOUT = "//*[text()='Logout']" ; 		
	public static final String BTN_REFRESH = "//*[text()='Refresh']";
	public static final String BTN_DEALER = "//*[text()='Dealer']";
	public static final String BTN_SEARCH = "//*[text()='Search'][1]";
	public static final String BTN_ADD_DEALER = "//*[text()='Add'][1]";
	public static final String TXT_DBA ="//*[@name='companyName']";
	public static final String BTN_DLRSRCH="//*[text()='Search'][1]";
	public static final String DBA_TXT ="//span[@class='screenDescription']" ;
	public static final String DBA_Name_Input_Field ="(//*[@class='x-form-item-body '])[2]//input" ;
    public static final String Legal_Name ="(//*[@class='x-form-item-body '])[3]//input" ;
    public static final String Courier_Account ="(//*[@class='x-form-item-body '])[11]//input" ;
    public static final String Federal_ID_Country="(//*[@class='x-form-trigger-input-cell'])[3]//input";
    public static final String Company_Type="(//*[@class='x-form-trigger-input-cell'])[6]//input";
    public static final String Courier_UI ="(//*[@class='x-form-trigger-input-cell'])[7]//input" ;
    public static final String Business_Start_UI ="(//*[@class='x-form-trigger-input-cell'])[8]//input" ;
    public static final String Licences_Link="(//*[text()='Licenses'])[1]";
    public static final String License_Type_TXT ="(//*[@class='x-grid-table x-grid-table-resizer'])[2]//tr[2]//td[2]//div";
    public static final String License_Number_TXT ="(//*[@class='x-grid-table x-grid-table-resizer'])[2]//tr[2]//td[3]//div";
    public static final String License_State_TXT ="(//*[@class='x-grid-table x-grid-table-resizer'])[2]//tr[2]//td[7]//div";
    public static final String License_Country_TXT ="(//*[@class='x-grid-table x-grid-table-resizer'])[2]//tr[2]//td[8]//div";
    public static final String Dealer_Add="(//*[text()='Add'])[1]";
    public static final String Company="//*[@class='x-boundlist-item']";
    
    public static final String Identification_DBA_Name="//*[@name='companyName']";
    public static final String Identification_LegalName="//*[@name='legalName']";
    public static final String Federal_Id_Country_WebElements="//*[@class='x-boundlist-list-ct']//ul//li";
    public static final String Federal_Id_Country="//*[@name='governmentIdCountry']";
    public static final String Federal_Id_Type="//*[@name='governmentIdType']";
    public static final String SameAsDBA="(//*[@class='x-form-field x-form-checkbox'])[1]";
    public static final String FedaralId="//*[@name='governmentId']";
    public static final String FedaralIdConfirm="//*[@name='governmentIdConfirm']";
    public static final String companyType="//*[@name='companyType']";
    public static final String FederalIdCountry_dropdown="(//*[@class='x-trigger-cell'])[1]";
    public static final String BusinessType="(//*[text()='Business Types'])[1]";
    public static final String Add_BusinessType="(//*[text()='Add'])[2]";
    public static final String TXT_BusinessType="(//span[text()='Business Types'])[2]";
    public static final String DealerType_DropdownArrow="(//*[@class='x-trigger-index-0 x-form-trigger x-form-arrow-trigger x-form-trigger-last x-unselectable'])[7]";
    public static final String DealerType="(//div[@class='x-boundlist-list-ct'])[3]//li";
    public static final String NewCheckBox="(//*[@class='x-form-field x-form-checkbox'])[4]";
    public static final String DelearType_Update="//*[text()='Update']";
    public static final String DelearType_Save="//*[text()='Save']";
    public static final String IdentificationNext="//*[text()='Next']";
    public static final String CompanyTypeText="//*[text()='Company Type:']";
    public static final String BondCompany="//*[@name='bondingCompany']";
    public static final String BondNumber="//*[@name='bondNumber']";
    public static final String IdentificationWindow="//*[text()='Identification']";
    public static final String ContactWindow="//*[text()='Contact']";
    
    public static final String AddressAdd="(//span[text()='Add'])[1]";
    
    public static final String AddressTypeDropdownArrow="(//*[@class='x-trigger-cell'])[15]";
    
    public static final String AddressTypeValue="(//*[@class='x-boundlist-list-ct'])[2]//ul//li";
    
    public static final String changeRoleDropdown="(//div[contains(@id,'button')])[3]";
    
    public static final String changeRole="//*[text()='Change Role']";
    
    public static final String HomeCompanyDropdown="(//*[@class='x-trigger-cell'])[15]";
    
    public static final String HomeCompanyValue="//*[@class='x-boundlist-list-ct']//ul//li";
  
    public static final String Ok="//*[text()='Ok' or text()='OK']";
    
    public static final String Register="//*[text()='Register']";
    
    public static final String QuickSearch="//*[contains(@name,'numberfield')]";
    
    public static final String LOT_Line1 =  "(//*[@name='street1'])[1]" ;

    public static final String LOT_Line2 =  "(//*[@name='street2'])[1]" ;
    
    public static final String LOT_City =  "(//*[@name='city'])[1]" ;

    public static final String LOT_Country="(//*[@name='country'])[1]" ;

    public static final String LOT_State = "(//*[@name='state'])[1]";
    
    public static final String StateProvince_Text = "(//*[text()='State/Province:'])[1]";
    
    
    public static final String LOT_PostalCode =  "(//*[@name='postalCode'])[1]" ;

    public static final String Business_PhoneCountry ="(//*[@name='country'])[3]";

    public static final String Business_PhoneNumber =  "(//*[@name='value'])[1]" ; 

    public static final String Next_Contact = "(//*[@class='x-btn-center'])[38]" ;
    
    public static final String Fax_BusinessCountryDropdownArrow ="(//*[@class='x-trigger-cell'])[12]";
    
    public static final String Fax_BusinessCountry ="(//*[@class='x-boundlist-list-ct'])[5]/ul/li";

    public static final String BusinessFax ="(//*[@name='value'])[3]";
    
    public static final String MailingSameAsLot ="(//*[@class='x-form-field x-form-checkbox'])[3]";
    
    public static final String Contact_Email ="(//*[@name='value'])[2]";
    
    public static final String WebsiteURL ="(//*[@name='value'])[4]";
    
    // License info for Dealer
    public static final String LicenseWindow="//*[text()='Licenses']";
    
    public static final String LicenseNumber="//*[@name='idNumber']";

    public static final String issuedDate="//*[@name='issuedDate']";

    public static final String  expirationDate = "//*[@name='expirationDate']";

    public static final String  issuingCountry = "(//*[@class='x-boundlist x-boundlist-floating x-layer x-boundlist-default'])[6]//ul//li" ;
    
    public static final String  issuingCountryDropdownArrow =  "(//*[@class='x-trigger-cell'])[17]" ;

    public static final String  issuingStateOrProvince =  "(//*[@class='x-boundlist x-boundlist-floating x-layer x-boundlist-default'])[7]//ul//li" ;
    
    public static final String  issuingStateOrProvinceArrowDropdown =  "(//*[@class='x-trigger-cell'])[18]" ;
    
    public static final String  DealerCreateFinish =  "//*[text()='Finish']" ;
    
    
    
    
    public static final String  DealerOverride =  "//*[text()='Override']" ;
  
    public static final String  CountryDropdownArrow =  "(//*[@class='x-trigger-cell'])[5]" ;
    
    public static final String  Country =  "(//*[@class='x-boundlist-list-ct'])[3]/ul/li" ;
    
    public static final String  StateProvinceDropdownArrow =  "(//*[@class='x-trigger-cell'])[6]" ;
    
    public static final String  StateProvince =  "(//*[@class='x-boundlist-list-ct'])[4]/ul/li" ;
    
    public static final String  TextAfterDealerCreation =  "(//*[contains(text(),'Dealer')])[2]" ;
    
    public static final String  LogoutDropdownArrow =  "(//*[@class='x-btn-arrow x-btn-arrow-right'])[1]" ;
    
    public static final String  Logout_AA =  "//*[text()='Logout']" ;
    
    public static final String  Search_ID =  "//*[@name='id']" ;
    
    public static final String  Federal_ID =  "//*[@name='governmentId']" ;
    
    //bonding Company name fetch after creation
    public static final String  bondingCompany =  "//*[@name='bondingCompany']" ;
    
    //bond number fetch after creation
    public static final String  bondNumber =  "//*[@name='bondNumber']" ;
    
    public static final String  bondExpirationDate =  "//*[@name='bondExpirationDate']" ;
    
    public static final String  insuranceCompany =  "//*[@name='insuranceCompany']" ;
    
    public static final String  insurancePolicyNumber =  "//*[@name='insurancePolicyNumber']" ;
    
    public static final String  insurancePolicyExpirationDate =  "//*[@name='insurancePolicyExpirationDate']" ;
  
    public static final String  BusinessTypes =  "(//*[text()='Business Types'])[1]" ;
    
    public static final String  DealerType_underBusinessType =  "(//*[@class='x-grid-view x-fit-item x-grid-view-default'])[1]//tbody//tr//td[2]//div" ;
    
    public static final String  DealerType_EditIcon =  "((//*[@class='x-grid-view x-fit-item x-grid-view-default'])[1]//tbody//tr//td//div[1]//img)[1]" ;
    
    public static final String  Types_Under_EditBusinessTypeWindow =  "(//*[text()='New'])[2]" ;
    
    public static final String  CancelButton_Under_EditType_Window =  "(//*[text()='Cancel'])[2]" ;
    
    public static final String  ContactOptionsLink =  "(//*[text()='Contact Options'])[1]" ;
    
    public static final String Addresses_Link = "(//*[text()='Addresses'])[1]" ;
    
    public static final String Addressline1_TXT = "(//table[@class='x-grid-table x-grid-table-resizer'])[2]/tbody/tr[2]/td[2]";
    
    public static final String Addressline2_TXT = "(//table[@class='x-grid-table x-grid-table-resizer'])[2]/tbody/tr[2]/td[3]";
    
    public static final String AddressesCity_TXT = "(//table[@class='x-grid-table x-grid-table-resizer'])[2]/tbody/tr[2]/td[4]";
    
    public static final String StateProvince_TXT = "(//table[@class='x-grid-table x-grid-table-resizer'])[2]/tbody/tr[2]/td[6]";
    
    public static final String PostalCode_TXT = "(//table[@class='x-grid-table x-grid-table-resizer'])[2]/tbody/tr[2]/td[7]";
    
    public static final String Country_TXT = "(//table[@class='x-grid-table x-grid-table-resizer'])[2]/tbody/tr[2]/td[8]";
    
    
    public static final String MailAddressline1_TXT = "(//table[@class='x-grid-table x-grid-table-resizer'])[3]/tbody/tr[2]/td[2]";
    
    public static final String MailAddressline2_TXT = "(//table[@class='x-grid-table x-grid-table-resizer'])[3]/tbody/tr[2]/td[3]";
    
    public static final String MailAddressesCity_TXT = "(//table[@class='x-grid-table x-grid-table-resizer'])[3]/tbody/tr[2]/td[4]";
    
    public static final String MailStateProvince_TXT = "(//table[@class='x-grid-table x-grid-table-resizer'])[3]/tbody/tr[2]/td[6]";
    
    public static final String MailPostalCode_TXT = "(//table[@class='x-grid-table x-grid-table-resizer'])[3]/tbody/tr[2]/td[7]";
    
    public static final String MailCountry_TXT = "(//table[@class='x-grid-table x-grid-table-resizer'])[3]/tbody/tr[2]/td[8]";
    
    public static final String AddButton_ContactDetails = "(//*[text()='Add'])[1]";
    
    public static final String AddContactOption = "//*[text()='Add Contact Option']";
    
    public static final String visibilitydropdownarrow = "(//*[@class='x-trigger-cell'])[14]";
    
    public static final String visibilityValue = "//*[@class='x-boundlist-list-ct']//ul//li";
    
    public static final String locationValue = "(//*[@class='x-boundlist-list-ct'])[2]/ul/li";
    
    public static final String Locationdropdownarrow = "(//*[@class='x-trigger-cell'])[15]";
    
    public static final String Typedropdownarrow = "(//*[@class='x-trigger-cell'])[16]";
    
    public static final String TypeValue = "(//*[contains(@class,'x-boundlist x-boundlist-floating')])[3]//div//ul//li";
    
    public static final String Countrydropdownarrow = "(//*[@class='x-trigger-cell'])[17]";
    
    public static final String Countrydropdownvalue = "(//*[contains(@class,'x-boundlist x-boundlist-floating')])[4]//div//ul//li";
    
    public static final String PhoneNumber = "//*[@name='phoneValue']";
    
    public static final String SaveButtonUnderAddContactOption = "(//*[text()='Save'])[2]";
    
    public static final String WebSite = "//*[@name='urlValue']";
    
    public static final String Error = "//*[contains(text(),'already exists. Please edit the existing record.')]";
}
