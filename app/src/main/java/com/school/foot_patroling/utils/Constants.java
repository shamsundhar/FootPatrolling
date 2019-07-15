package com.school.foot_patroling.utils;

/**
 * Created by shyam on 2/22/2018.
 */

public class Constants {
    public static final String BUNDLE_KEY_REG_ID = "BUNDLE_REG_ID";
    public static final String BUNDLE_KEY_AUTH = "BUNDLE_AUTH";
    public static final String BUNDLE_KEY_URL = "BUNDLE_URL";
    public static final String BUNDLE_KEY_IMEI1 = "BUNDLE_IMEI1";
    public static final String BUNDLE_KEY_IMEI2 = "BUNDLE_IMEI2";
    public static final String BUNDLE_KEY_SELECTED_IMEI = "BUNDLE_SELECTED_IMEI";
    public static final String BUNDLE_KEY_CURRENT_SYNC_TIME = "BUNDLE_CURRENT_SYNC_TIME";
    public static final String FOOTPATROLLING_DATABASE = "FootPatrollingDB";

    public static final String INITIAL_TIME = "31-01-1990 17:26:15.613";

    public static final String BUNDLE_KEY_SHOP_NAME = "BUNDLE_SHOP_NAME";
    public static final String BUNDLE_KEY_SHOP_ID = "BUNDLE_SHOP_ID";
    public static final String BUNDLE_KEY_TOTAL_PAYABLE_AMOUNT = "BUNDLE_TOTAL_PAYABLE_AMOUNT";
    public static final String BUNDLE_KEY_DISPLAY_ORDER_SUCCESS_POPUP = "BUNDLE_DISPLAY_ORDER_POPUP";
    public static final String BUNDLE_KEY_OTP = "BUNDLE_OTP";
    public static final String BUNDLE_KEY_USER_ID = "BUNDLE_USER_ID";
    public static final String BUNDLE_KEY_RESET_PASSWORD_RESULT_FLAG = "RESET_PASSWORD_RESULT_FLAG";

    public static final String BUNDLE_KEY_LAST_SYNC_DATE = "BUNDLE_LAST_SYNC_DATE";

    public static final String PREF_KEY_LOGIN_ID = "KEY_LOGIN_ID";
    public static final String PREF_KEY_TOKEN = "KEY_TOKEN";
    public static final String PREF_KEY_FP_STARTED = "KEY_FP_STARTED";
    public static final String PREF_KEY_FP_STARTED_TIME = "KEY_FP_STARTED_TIME";

    public static final String CONST_CUSTOMER_USER_TYPE = "1";
    public static final String BUNDLE_KEY_OTP_VERIFIED_MESSAGE = "OTP_VERIFIED_MESSAGE";

    public static final String BUNDLE_KEY_DISPLAY_FRAGMENT = "DISPLAY_FRAGMENT";
    public static final String BUNDLE_KEY_SELECTED_COMPLIANCE = "SELECTED_COMPLIANCE";
    public static final String BUNDLE_VALUE_COMPLIANCE = "COMPLIANCE";
    public static final String BUNDLE_VALUE_EDIT_OBSERVATION = "EDIT_OBSERVATION";
    public static final String BUNDLE_KEY_SELECTED_OBSERVATION = "SELECTED_OBSERVATION";

    public static final String DATE_FORMAT1 = "EEEE, d MMMM";
    public static final String DATE_FORMAT2 = "MM-dd-yyyy";
    public static final String DATE_FORMAT3 = "dd-MMMM-yyyy";

    //Rest Services
    public static final String REST_GET_FP_DATA = "/warehouse/fpApp/get-fp-data";
    public static final String REST_GET_REPORT_NAMES = "/warehouse/fpApp/get-report-names";
    public static final String REST_REPORT_EXECUTION = "/warehouse/fpApp/report-execution";
    public static final String REST_POST_FILE_UPLOAD = "/file-upload";


    public static String USER_LOGIN_INSERT_SQL = "insert into user_login( user_login_id, current_password, password_hint, is_system, enabled, last_updated_stamp, last_updated_tx_stamp, " +
            "created_stamp, created_tx_stamp) " +
            "values(?,?,?,?,?,?,?,?,?)";

    public static String USER_LOGIN_UPDATE_SQL = "update user_login set current_password = ?, password_hint = ?," +
            "is_system = ?, enabled = ?, last_updated_stamp = ?, last_updated_tx_stamp = ?, created_stamp = ?, created_tx_stamp = ? where user_login_id = ?";

    public static String PRODUCT_INSERT_SQL = "insert into product (product_id, product_type_id, internal_name, product_name, bill_of_material_level, " +
            "is_active, product_code_type_id, pl_no, rly_id, trd_div_id, last_updated_stamp, last_updated_tx_stamp, " +
            "created_stamp, created_tx_stamp ) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

    public static String CHECKLIST_INSERT_SQL = "insert into observations_check_list (seq_id, inspection_type, observation_category, observation_item, description, " +
            "from_date, thru_date, priority, display_sequence ) values (?,?,?,?,?,?,?,?,?)";


    public static String PRODUCT_UPDATE_SQL = "update product set product_type_id = ?, internal_name = ?, product_name = ?, " +
            "bill_of_material_level = ?, is_active = ?, product_code_type_id = ?, pl_no = ?, rly_id = ?, trd_div_id = ?, last_updated_stamp = ?, " +
            "last_updated_tx_stamp = ?, created_stamp = ?, created_tx_stamp = ? where product_id = ?";
    public static String FACILITY_INSERT_SQL = "insert into  facility (facility_id,facility_type_id,parent_facility_id,owner_party_id,default_inventory_item_type_id,facility_name, " +
            "last_updated_stamp,last_updated_tx_stamp,created_stamp,created_tx_stamp,closed_date,default_days_to_ship,default_dimension_uom_id,default_weight_uom_id,  " +
            " depot_type,description,facility_size, facility_size_uom_id,geo_point_id,is_disable,manuf_alloc_enable,opened_date,organized,primary_facility_group_id,product_store_id,remarks,reserve_order_enum_id,skip_pack_inv_check,square_footage) "+
            "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

    public static String FACILITY_UPDATE_SQL = "update facility  set facility_type_id = ?, parent_facility_id = ?, owner_party_id = ?, " +
            "default_inventory_item_type_id = ?, facility_name = ?, last_updated_stamp = ?, last_updated_tx_stamp = ?, created_stamp = ?, created_tx_stamp = ?, closed_date = ?, default_days_to_ship = ?, default_dimension_uom_id = ?, default_weight_uom_id = ?,  " +
            " depot_type = ?, description = ?, facility_size = ?, facility_size_uom_id = ?, geo_point_id = ?, is_disable = ?, manuf_alloc_enable = ?, opened_date = ?, organized = ?, primary_facility_group_id = ?, product_store_id = ?, remarks = ?, reserve_order_enum_id = ?, skip_pack_inv_check = ?, square_footage = ? where facility_id = ?";



}
