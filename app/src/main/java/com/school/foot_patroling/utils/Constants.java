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
    public static final String FOOTPATROLLING_DATABASE = "FootPatrollingDB";
    public static final String BUNDLE_KEY_CURRENT_SYNC_TIME = "BUNDLE_CURRENT_SYNC_TIME";


    public static final String BUNDLE_KEY_SHOP_NAME = "BUNDLE_SHOP_NAME";
    public static final String BUNDLE_KEY_SHOP_ID = "BUNDLE_SHOP_ID";
    public static final String BUNDLE_KEY_TOTAL_PAYABLE_AMOUNT = "BUNDLE_TOTAL_PAYABLE_AMOUNT";
    public static final String BUNDLE_KEY_DISPLAY_ORDER_SUCCESS_POPUP = "BUNDLE_DISPLAY_ORDER_POPUP";
    public static final String BUNDLE_KEY_OTP = "BUNDLE_OTP";
    public static final String BUNDLE_KEY_USER_ID = "BUNDLE_USER_ID";
    public static final String BUNDLE_KEY_RESET_PASSWORD_RESULT_FLAG = "RESET_PASSWORD_RESULT_FLAG";

    public static final String PREF_KEY_LOGIN_ID = "KEY_LOGIN_ID";
    public static final String PREF_KEY_TOKEN = "KEY_TOKEN";

    public static final String CONST_CUSTOMER_USER_TYPE = "1";
    public static final String BUNDLE_KEY_OTP_VERIFIED_MESSAGE = "OTP_VERIFIED_MESSAGE";


    public static String USER_LOGIN_INSERT_SQL = "insert into user_login( user_login_id, current_password, password_hint, is_system, enabled, last_updated_stamp, last_updated_tx_stamp, " +
            "created_stamp, created_tx_stamp) " +
            "values(?,?,?,?,?,?,?,?,?)";

    public static String USER_LOGIN_UPDATE_SQL = "update user_login set current_password = ?, password_hint = ?," +
            "is_system = ?, enabled = ?, last_updated_stamp = ?, last_updated_tx_stamp = ?, created_stamp = ?, created_tx_stamp = ? where user_login_id = ?";

    public static String PRODUCT_INSERT_SQL = "insert into product (product_id, product_type_id, internal_name, product_name, bill_of_material_level, " +
            "is_active, product_code_type_id, pl_no, rly_id, trd_div_id, last_updated_stamp, last_updated_tx_stamp, " +
            "created_stamp, created_tx_stamp ) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

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
