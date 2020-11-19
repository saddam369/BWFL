package com.mentor.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.json.JSONArray;
import org.json.JSONObject;

import com.mentor.resource.ConnectionToDataBase;

public class CasecodeAgainstBottleCodeImpl {
	public String getCasecode(String caseno) {
		int unitid = 0;
		int unitid1 = 0;
		
		String responseCaseCode="";
		String plan_date = null;
		String bottledate="";
		String etin = caseno.substring(0, 12);
		String licence_type = etin.substring(3, 4);
		String casecode = caseno.substring(23, caseno.length() - 1);
		String casecode1 = caseno.substring(21, caseno.length());
		//String casecode = caseno.substring(26, caseno.length());
		if (caseno.length() == 35) {
			unitid = Integer.parseInt(caseno.substring(4, 6));
			unitid1 = Integer.parseInt(caseno.substring(13, 16));
		} else {
			unitid = Integer.parseInt(caseno.substring(4, 6));
			unitid1 = Integer.parseInt(caseno.substring(18, 21));
		}
		try {
			
			SimpleDateFormat sdf5 = new SimpleDateFormat("yyMMdd");
			SimpleDateFormat sdf6 = new SimpleDateFormat("yyyy-MM-dd");
			 bottledate=caseno.substring(12, 18);
			Date date = sdf5.parse(bottledate);
			plan_date = sdf6.format(date);


		} catch (Exception e) {

			e.printStackTrace();
		}

		String brewary_fl3 =

				" SELECT recv_id,ws_date,ws_gatepass,shop_id,shop_type,plan_id, date_plan, etin, casecode, x.bottle_codee, fl11gatepass,fl11_date, fl36gatepass, fl36_date, boxing_seq                      "
						+ " from(SELECT recv_id,ws_date,ws_gatepass,shop_id,shop_type,plan_id, date_plan, etin, casecode, bottle_code, fl11gatepass,regexp_split_to_table(bottle_code , '[\\|s,]+')as  bottle_codee,  "
						+ " fl11_date, fl36gatepass, fl36_date, boxing_seq                                                                                              "
						+ " FROM bottling_unmapped.brewary_unmap_fl3 where etin='"
						+ etin
						+ "'   and date_plan='"
						+ plan_date
						+ "' ) x where  bottle_codee='"
						+ casecode
						+ "'   "
						+ "  union all "
						+

						" SELECT recv_id,ws_date,ws_gatepass,shop_id,shop_type,plan_id, date_plan, etin, casecode, x.bottle_codee, fl11gatepass,fl11_date, fl36gatepass, fl36_date, boxing_seq                      "
						+ " from(SELECT recv_id,ws_date,ws_gatepass,shop_id,shop_type,plan_id, date_plan, etin, casecode, bottle_code, fl11gatepass,regexp_split_to_table(bottle_code , '[\\|s,]+')as  bottle_codee,  "
						+ " fl11_date, fl36gatepass, fl36_date, boxing_seq                                                                                              "
						+ " FROM bottling_unmapped.brewary_unmap_fl3 where etin='"
						+ etin
						+ "'   and date_plan='"
						+ plan_date
						+ "' ) x where  bottle_codee='"
						+ casecode1
						+ "' union "
						+

						" SELECT recv_id,ws_date,ws_gatepass,shop_id,shop_type,plan_id, date_plan, etin, casecode, x.bottle_codee, fl11gatepass,fl11_date, fl36gatepass, fl36_date, boxing_seq                      "
						+ " from(SELECT recv_id,ws_date,ws_gatepass,shop_id,shop_type,plan_id, date_plan, etin, casecode, bottle_code, fl11gatepass,regexp_split_to_table(bottle_code , '[\\|s,]+')as  bottle_codee,  "
						+ " fl11_date, fl36gatepass, fl36_date, boxing_seq                                                                                              "
						+ " FROM bottling_unmapped.brewary_unmap_fl3_backup where etin='"
						+ etin
						+ "'   and date_plan='"
						+ plan_date
						+ "' ) x where  bottle_codee='"
						+ casecode
						+ "'   "
						+ "  union all "
						+

						" SELECT recv_id,ws_date,ws_gatepass,shop_id,shop_type,plan_id, date_plan, etin, casecode, x.bottle_codee, fl11gatepass,fl11_date, fl36gatepass, fl36_date, boxing_seq                      "
						+ " from(SELECT recv_id,ws_date,ws_gatepass,shop_id,shop_type,plan_id, date_plan, etin, casecode, bottle_code, fl11gatepass,regexp_split_to_table(bottle_code , '[\\|s,]+')as  bottle_codee,  "
						+ " fl11_date, fl36gatepass, fl36_date, boxing_seq                                                                                              "
						+ " FROM bottling_unmapped.brewary_unmap_fl3_backup where etin='"
						+ etin
						+ "'   and date_plan='"
						+ plan_date
						+ "' ) x where  bottle_codee='" + casecode1 + "'";

				String brewary_fl3a =

				" SELECT recv_id,ws_date,ws_gatepass,shop_id,shop_type,plan_id, date_plan, etin, casecode, x.bottle_codee, fl11gatepass,fl11_date, fl36gatepass, fl36_date, boxing_seq                      "
						+ " from(SELECT recv_id,ws_date,ws_gatepass,shop_id,shop_type,plan_id, date_plan, etin, casecode, bottle_code, fl11gatepass,regexp_split_to_table(bottle_code , '[\\|s,]+')as  bottle_codee,  "
						+ " fl11_date, fl36gatepass, fl36_date, boxing_seq                                                                                              "
						+ " FROM bottling_unmapped.brewary_unmap_fl3a where etin='"
						+ etin
						+ "'   and date_plan='"
						+ plan_date
						+ "' ) x where  bottle_codee='"
						+ casecode
						+ "'"
						+ " union all "
						+

						" SELECT recv_id,ws_date,ws_gatepass,shop_id,shop_type,plan_id, date_plan, etin, casecode, x.bottle_codee, fl11gatepass,fl11_date, fl36gatepass, fl36_date, boxing_seq                      "
						+ " from(SELECT recv_id,ws_date,ws_gatepass,shop_id,shop_type,plan_id, date_plan, etin, casecode, bottle_code, fl11gatepass,regexp_split_to_table(bottle_code , '[\\|s,]+')as  bottle_codee,  "
						+ " fl11_date, fl36gatepass, fl36_date, boxing_seq                                                                                              "
						+ " FROM bottling_unmapped.brewary_unmap_fl3a where etin='"
						+ etin
						+ "'   and date_plan='"
						+ plan_date
						+ "' ) x where  bottle_codee='"
						+ casecode1
						+ "' union "
						+

						" SELECT recv_id,ws_date,ws_gatepass,shop_id,shop_type,plan_id, date_plan, etin, casecode, x.bottle_codee, fl11gatepass,fl11_date, fl36gatepass, fl36_date, boxing_seq                      "
						+ " from(SELECT recv_id,ws_date,ws_gatepass,shop_id,shop_type,plan_id, date_plan, etin, casecode, bottle_code, fl11gatepass,regexp_split_to_table(bottle_code , '[\\|s,]+')as  bottle_codee,  "
						+ " fl11_date, fl36gatepass, fl36_date, boxing_seq                                                                                              "
						+ " FROM bottling_unmapped.brewary_unmap_fl3a_backup where etin='"
						+ etin
						+ "'   and date_plan='"
						+ plan_date
						+ "' ) x where  bottle_codee='"
						+ casecode
						+ "'"
						+ " union all "
						+

						" SELECT recv_id,ws_date,ws_gatepass,shop_id,shop_type,plan_id, date_plan, etin, casecode, x.bottle_codee, fl11gatepass,fl11_date, fl36gatepass, fl36_date, boxing_seq                      "
						+ " from(SELECT recv_id,ws_date,ws_gatepass,shop_id,shop_type,plan_id, date_plan, etin, casecode, bottle_code, fl11gatepass,regexp_split_to_table(bottle_code , '[\\|s,]+')as  bottle_codee,  "
						+ " fl11_date, fl36gatepass, fl36_date, boxing_seq                                                                                              "
						+ " FROM bottling_unmapped.brewary_unmap_fl3a_backup where etin='"
						+ etin
						+ "'   and date_plan='"
						+ plan_date
						+ "' ) x where  bottle_codee='" + casecode1 + "'";
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						String disliry_cl =

				" SELECT recv_id,ws_date,ws_gatepass,shop_id,shop_type,plan_id, date_plan, etin, casecode, x.bottle_codee, fl11gatepass,fl11_date, fl36gatepass, fl36_date, boxing_seq                      "
						+ " from(SELECT recv_id,ws_date,ws_gatepass,shop_id,shop_type,plan_id, date_plan, etin, casecode, bottle_code, fl11gatepass,regexp_split_to_table(bottle_code , '[\\|s,]+')as  bottle_codee,  "
						+ " fl11_date, fl36gatepass, fl36_date, boxing_seq                                                                                              "
						+ " FROM bottling_unmapped.disliry_unmap_cl where etin='"
						+ etin
						+ "'   and date_plan='"
						+ plan_date
						+ "' ) x where  bottle_codee='"
						+ casecode
						+ "'"
						+ "union all "
						+

						" SELECT recv_id,ws_date,ws_gatepass,shop_id,shop_type,plan_id, date_plan, etin, casecode, x.bottle_codee, fl11gatepass,fl11_date, fl36gatepass, fl36_date, boxing_seq                      "
						+ " from(SELECT recv_id,ws_date,ws_gatepass,shop_id,shop_type,plan_id, date_plan, etin, casecode, bottle_code, fl11gatepass,regexp_split_to_table(bottle_code , '[\\|s,]+')as  bottle_codee,  "
						+ " fl11_date, fl36gatepass, fl36_date, boxing_seq                                                                                              "
						+ " FROM bottling_unmapped.disliry_unmap_cl where etin='"
						+ etin
						+ "'   and date_plan='"
						+ plan_date
						+ "' ) x where  bottle_codee='"
						+ casecode1
						+ "' union "
						+

						" SELECT recv_id,ws_date,ws_gatepass,shop_id,shop_type,plan_id, date_plan, etin, casecode, x.bottle_codee, fl11gatepass,fl11_date, fl36gatepass, fl36_date, boxing_seq                      "
						+ " from(SELECT recv_id,ws_date,ws_gatepass,shop_id,shop_type,plan_id, date_plan, etin, casecode, bottle_code, fl11gatepass,regexp_split_to_table(bottle_code , '[\\|s,]+')as  bottle_codee,  "
						+ " fl11_date, fl36gatepass, fl36_date, boxing_seq                                                                                              "
						+ " FROM bottling_unmapped.disliry_unmap_cl_backup where etin='"
						+ etin
						+ "'   and date_plan='"
						+ plan_date
						+ "' ) x where  bottle_codee='"
						+ casecode
						+ "'"
						+ "union all"
						+

						" SELECT recv_id,ws_date,ws_gatepass,shop_id,shop_type,plan_id, date_plan, etin, casecode, x.bottle_codee, fl11gatepass,fl11_date, fl36gatepass, fl36_date, boxing_seq                      "
						+ " from(SELECT recv_id,ws_date,ws_gatepass,shop_id,shop_type,plan_id, date_plan, etin, casecode, bottle_code, fl11gatepass,regexp_split_to_table(bottle_code , '[\\|s,]+')as  bottle_codee,  "
						+ " fl11_date, fl36gatepass, fl36_date, boxing_seq                                                                                              "
						+ " FROM bottling_unmapped.disliry_unmap_cl_backup where etin='"
						+ etin
						+ "'   and date_plan='"
						+ plan_date
						+ "' ) x where  bottle_codee='" + casecode1 + "'";

				String disliry_fl3 =

				" SELECT recv_id,ws_date,ws_gatepass,shop_id,shop_type,plan_id, date_plan, etin, casecode, x.bottle_codee, fl11gatepass,fl11_date, fl36gatepass, fl36_date, boxing_seq                      "
						+ " from(SELECT recv_id,ws_date,ws_gatepass,shop_id,shop_type,plan_id, date_plan, etin, casecode, bottle_code, fl11gatepass,regexp_split_to_table(bottle_code , '[\\|s,]+')as  bottle_codee,  "
						+ " fl11_date, fl36gatepass, fl36_date, boxing_seq                                                                                              "
						+ " FROM bottling_unmapped.disliry_unmap_fl3 where etin='"
						+ etin
						+ "'   and date_plan='"
						+ plan_date
						+ "' ) x where  bottle_codee='"
						+ casecode
						+ "'"
						+ "union all"
						+ " SELECT recv_id,ws_date,ws_gatepass,shop_id,shop_type,plan_id, date_plan, etin, casecode, x.bottle_codee, fl11gatepass,fl11_date, fl36gatepass, fl36_date, boxing_seq                      "
						+ " from(SELECT recv_id,ws_date,ws_gatepass,shop_id,shop_type,plan_id, date_plan, etin, casecode, bottle_code, fl11gatepass,regexp_split_to_table(bottle_code , '[\\|s,]+')as  bottle_codee,  "
						+ " fl11_date, fl36gatepass, fl36_date, boxing_seq                                                                                              "
						+ " FROM bottling_unmapped.disliry_unmap_fl3 where etin='"
						+ etin
						+ "'   and date_plan='"
						+ plan_date
						+ "' ) x where  bottle_codee='"
						+ casecode1
						+ "' union "
						+

						" SELECT recv_id,ws_date,ws_gatepass,shop_id,shop_type,plan_id, date_plan, etin, casecode, x.bottle_codee, fl11gatepass,fl11_date, fl36gatepass, fl36_date, boxing_seq                      "
						+ " from(SELECT recv_id,ws_date,ws_gatepass,shop_id,shop_type,plan_id, date_plan, etin, casecode, bottle_code, fl11gatepass,regexp_split_to_table(bottle_code , '[\\|s,]+')as  bottle_codee,  "
						+ " fl11_date, fl36gatepass, fl36_date, boxing_seq                                                                                              "
						+ " FROM bottling_unmapped.disliry_unmap_fl3_backup where etin='"
						+ etin
						+ "'   and date_plan='"
						+ plan_date
						+ "' ) x where  bottle_codee='"
						+ casecode
						+ "'"
						+ "union all"
						+ " SELECT recv_id,ws_date,ws_gatepass,shop_id,shop_type,plan_id, date_plan, etin, casecode, x.bottle_codee, fl11gatepass,fl11_date, fl36gatepass, fl36_date, boxing_seq                      "
						+ " from(SELECT recv_id,ws_date,ws_gatepass,shop_id,shop_type,plan_id, date_plan, etin, casecode, bottle_code, fl11gatepass,regexp_split_to_table(bottle_code , '[\\|s,]+')as  bottle_codee,  "
						+ " fl11_date, fl36gatepass, fl36_date, boxing_seq                                                                                              "
						+ " FROM bottling_unmapped.disliry_unmap_fl3_backup where etin='"
						+ etin
						+ "'   and date_plan='"
						+ plan_date
						+ "' ) x where  bottle_codee='" + casecode1 + "'";

				String disliry_fl3a =

				" SELECT recv_id,ws_date,ws_gatepass,shop_id,shop_type,plan_id, date_plan, etin, casecode, x.bottle_codee, fl11gatepass,fl11_date, fl36gatepass, fl36_date, boxing_seq                      "
						+ " from(SELECT recv_id,ws_date,ws_gatepass,shop_id,shop_type,plan_id, date_plan, etin, casecode, bottle_code, fl11gatepass,regexp_split_to_table(bottle_code , '[\\|s,]+')as  bottle_codee,  "
						+ " fl11_date, fl36gatepass, fl36_date, boxing_seq                                                                                              "
						+ " FROM bottling_unmapped.disliry_unmap_fl3a where etin='"
						+ etin
						+ "'   and date_plan='"
						+ plan_date
						+ "' ) x where  bottle_codee='"
						+ casecode
						+ "'"
						+ "union all"
						+ " SELECT recv_id,ws_date,ws_gatepass,shop_id,shop_type,plan_id, date_plan, etin, casecode, x.bottle_codee, fl11gatepass,fl11_date, fl36gatepass, fl36_date, boxing_seq                      "
						+ " from(SELECT recv_id,ws_date,ws_gatepass,shop_id,shop_type,plan_id, date_plan, etin, casecode, bottle_code, fl11gatepass,regexp_split_to_table(bottle_code , '[\\|s,]+')as  bottle_codee,  "
						+ " fl11_date, fl36gatepass, fl36_date, boxing_seq                                                                                              "
						+ " FROM bottling_unmapped.disliry_unmap_fl3a where etin='"
						+ etin
						+ "'   and date_plan='"
						+ plan_date
						+ "' ) x where  bottle_codee='"
						+ casecode1
						+ "' union "
						+

						" SELECT recv_id,ws_date,ws_gatepass,shop_id,shop_type,plan_id, date_plan, etin, casecode, x.bottle_codee, fl11gatepass,fl11_date, fl36gatepass, fl36_date, boxing_seq                      "
						+ " from(SELECT recv_id,ws_date,ws_gatepass,shop_id,shop_type,plan_id, date_plan, etin, casecode, bottle_code, fl11gatepass,regexp_split_to_table(bottle_code , '[\\|s,]+')as  bottle_codee,  "
						+ " fl11_date, fl36gatepass, fl36_date, boxing_seq                                                                                              "
						+ " FROM bottling_unmapped.disliry_unmap_fl3a_backup where etin='"
						+ etin
						+ "'   and date_plan='"
						+ plan_date
						+ "' ) x where  bottle_codee='"
						+ casecode
						+ "'"
						+ "union all"
						+ " SELECT recv_id,ws_date,ws_gatepass,shop_id,shop_type,plan_id, date_plan, etin, casecode, x.bottle_codee, fl11gatepass,fl11_date, fl36gatepass, fl36_date, boxing_seq                      "
						+ " from(SELECT recv_id,ws_date,ws_gatepass,shop_id,shop_type,plan_id, date_plan, etin, casecode, bottle_code, fl11gatepass,regexp_split_to_table(bottle_code , '[\\|s,]+')as  bottle_codee,  "
						+ " fl11_date, fl36gatepass, fl36_date, boxing_seq                                                                                              "
						+ " FROM bottling_unmapped.disliry_unmap_fl3a_backup where etin='"
						+ etin
						+ "'   and date_plan='"
						+ plan_date
						+ "' ) x where  bottle_codee='" + casecode1 + "'";
						
						String bwfl = " SELECT recv_id,ws_date,ws_gatepass,shop_id,shop_type,plan_id, date_plan, etin, casecode, x.bottle_codee, fl11gatepass,fl11_date, fl36gatepass, fl36_date                     "
						+ " from(SELECT recv_id,ws_date,ws_gatepass,shop_id,shop_type,plan_id, date_plan, etin, casecode, bottle_code, fl11gatepass,regexp_split_to_table(bottle_code , '[\\|s,]+')as  bottle_codee,  "
						+ " fl11_date, fl36gatepass, fl36_date                                                                                              "
						+ " FROM bottling_unmapped.bwfl where etin='"
						+ etin
						+ "'   and date_plan='"
						+ plan_date
						+ "' ) x where  bottle_codee='"
						+ casecode
						+ "'"
						+ "union all "
						+ " SELECT recv_id,ws_date,ws_gatepass,shop_id,shop_type,plan_id, date_plan, etin, casecode, x.bottle_codee, fl11gatepass,fl11_date, fl36gatepass, fl36_date                     "
						+ " from(SELECT recv_id,ws_date,ws_gatepass,shop_id,shop_type,plan_id, date_plan, etin, casecode, bottle_code, fl11gatepass,regexp_split_to_table(bottle_code , '[\\|s,]+')as  bottle_codee,  "
						+ " fl11_date, fl36gatepass, fl36_date                                                                                              "
						+ " FROM bottling_unmapped.bwfl where etin='"
						+ etin
						+ "'   and date_plan='"
						+ plan_date
						+ "' ) x where  bottle_codee='"
						+ casecode1
						+ "' union "
						+

						" SELECT recv_id,ws_date,ws_gatepass,shop_id,shop_type,plan_id, date_plan, etin, casecode, x.bottle_codee, fl11gatepass,fl11_date, fl36gatepass, fl36_date                     "
						+ " from(SELECT recv_id,ws_date,ws_gatepass,shop_id,shop_type,plan_id, date_plan, etin, casecode, bottle_code, fl11gatepass,regexp_split_to_table(bottle_code , '[\\|s,]+')as  bottle_codee,  "
						+ " fl11_date, fl36gatepass, fl36_date                                                                                              "
						+ " FROM bottling_unmapped.bwfl_backup where etin='"
						+ etin
						+ "'   and date_plan='"
						+ plan_date
						+ "' ) x where  bottle_codee='"
						+ casecode
						+ "'"
						+ "union all "
						+ " SELECT recv_id,ws_date,ws_gatepass,shop_id,shop_type,plan_id, date_plan, etin, casecode, x.bottle_codee, fl11gatepass,fl11_date, fl36gatepass, fl36_date                     "
						+ " from(SELECT recv_id,ws_date,ws_gatepass,shop_id,shop_type,plan_id, date_plan, etin, casecode, bottle_code, fl11gatepass,regexp_split_to_table(bottle_code , '[\\|s,]+')as  bottle_codee,  "
						+ " fl11_date, fl36gatepass, fl36_date                                                                                              "
						+ " FROM bottling_unmapped.bwfl_backup where etin='"
						+ etin
						+ "'   and date_plan='"
						+ plan_date
						+ "' ) x where  bottle_codee='" + casecode1 + "'";
						
						
						
						
						
						
						
						
						
						
						String fl2d = " SELECT recv_id,ws_date,ws_gatepass,shop_id,shop_type,plan_id, date_plan, etin, casecode, x.bottle_codee, fl11gatepass,fl11_date, fl36gatepass, fl36_date                     "
						+ " from(SELECT recv_id,ws_date,ws_gatepass,shop_id,shop_type,plan_id, date_plan, etin, casecode, bottle_code, fl11gatepass,regexp_split_to_table(bottle_code , '[\\|s,]+')as  bottle_codee,  "
						+ " fl11_date, fl36gatepass, fl36_date                                                                                              "
						+ " FROM bottling_unmapped.fl2d where etin='"
						+ etin
						+ "'   and date_plan='"
						+ plan_date
						+ "' ) x where  x.bottle_codee='"
						+ casecode
						+ "'"
						+ "union all"
						+ " SELECT recv_id,ws_date,ws_gatepass,shop_id,shop_type,plan_id, date_plan, etin, casecode, x.bottle_codee, fl11gatepass,fl11_date, fl36gatepass, fl36_date                     "
						+ " from(SELECT recv_id,ws_date,ws_gatepass,shop_id,shop_type,plan_id, date_plan, etin, casecode, bottle_code, fl11gatepass,regexp_split_to_table(bottle_code , '[\\|s,]+')as  bottle_codee,  "
						+ " fl11_date, fl36gatepass, fl36_date                                                                                              "
						+ " FROM bottling_unmapped.fl2d where etin='"
						+ etin
						+ "'   and date_plan='"
						+ plan_date
						+ "' ) x where  x.bottle_codee='"
						+ casecode1
						+ "' union "
						+

						" SELECT recv_id,ws_date,ws_gatepass,shop_id,shop_type,plan_id, date_plan, etin, casecode, x.bottle_codee, fl11gatepass,fl11_date, fl36gatepass, fl36_date                     "
						+ " from(SELECT recv_id,ws_date,ws_gatepass,shop_id,shop_type,plan_id, date_plan, etin, casecode, bottle_code, fl11gatepass,regexp_split_to_table(bottle_code , '[\\|s,]+')as  bottle_codee,  "
						+ " fl11_date, fl36gatepass, fl36_date                                                                                              "
						+ " FROM bottling_unmapped.fl2d_backup where etin='"
						+ etin
						+ "'   and date_plan='"
						+ plan_date
						+ "' ) x where  x.bottle_codee='"
						+ casecode
						+ "'"
						+ "union all"
						+ " SELECT recv_id,ws_date,ws_gatepass,shop_id,shop_type,plan_id, date_plan, etin, casecode, x.bottle_codee, fl11gatepass,fl11_date, fl36gatepass, fl36_date                     "
						+ " from(SELECT recv_id,ws_date,ws_gatepass,shop_id,shop_type,plan_id, date_plan, etin, casecode, bottle_code, fl11gatepass,regexp_split_to_table(bottle_code , '[\\|s,]+')as  bottle_codee,  "
						+ " fl11_date, fl36gatepass, fl36_date                                                                                              "
						+ " FROM bottling_unmapped.fl2d_backup where etin='"
						+ etin
						+ "'   and date_plan='"
						+ plan_date
						+ "' ) x where  x.bottle_codee='" + casecode1 + "'";

				;


		String sql =

		// NEW QUERY

		"	select brand_id_fk,package_id,coalesce(x.mrp,0)AS mrp, coalesce(x.brand_name,'NA')AS brand_name,                                 "
				+ "	 coalesce(x.licencee_dtl,'NA')AS licencee_dtl,  coalesce(x.strength,'0')as strength,                                             "
				+ "	 coalesce(x.license_category,'NA')AS license_category,                                                                           "
				+ "	 coalesce(x.license_number)AS license_number,coalesce(x.manufacturer_details,'NA')AS manufacturer_details,                       "
				+ "	 coalesce(x.package_name,'NA')AS package_name,coalesce(x.quantity,'0')AS quantity,                                               "
				+ "	 coalesce(x.code_generate_through,'NA')AS code_generate_through , coalesce(x.typee,'NA')AS typee,                                "
				+ "	 coalesce(x.distrct,'NA')AS distrct ,                                                                                            "
				+ "	 coalesce(x.vch_undertaking_name,'NA')AS vch_undertaking_name ,                                                                  "
				+ "	 coalesce(x.distrct2,'NA')AS distrct2                                                                                            "
				+ "	 from (select distinct brand_id_fk,package_id,b.rounded_mrp as mrp,a.brand_name,a.licencee_dtl,a.strength,license_category,      "
				+ "	 license_number,c.vch_undertaking_name as manufacturer_details,                                                                  "
				+ "	  case when b.package_type='1' then 'Glass Bottle'                                                                               "
				+ "	  when b.package_type='2' then 'CAN'                                                                                             "
				+ "	      when b.package_type='3' then 'Pet Bottle'                                                                                  "
				+ "	  when b.package_type='4' then 'Tetra Pack'                                                                                      "
				+ "	  when b.package_type is NULL then 'NA'                                                                                          "
				+ "	  end as package_name,quantity,code_generate_through ,                                                                           "
				+ "	  c.vch_undertaking_name,'DISTILLERY' as typee  ,                                                                                "
				+ "	  d.description  as distrct  ,  d.description  as distrct2                                                                       "
				+ "	  from  distillery.brand_registration_19_20 a,distillery.packaging_details_19_20 b ,                                             "
				+ "	  public.dis_mst_pd1_pd2_lic c    left outer join public.district d   on c.vch_unit_dist=d.districtid::text                      "
				+ "	  where a.brand_id=b.brand_id_fk     and a.license_category not in ('BEER') and a.distillery_id=c.int_app_id_f                   "
				+ "	  union all                                                                                                                      "
				+ "	  select distinct brand_id_fk,package_id,b.rounded_mrp as mrp,a.brand_name,a.licencee_dtl,a.strength,license_category,           "
				+ "	  license_number,c.brewery_nm as manufacturer_details,                                                                           "
				+ "	   case when b.package_type='1' then 'Glass Bottle'                                                                              "
				+ "	 when b.package_type='2' then 'CAN'                                                                                              "
				+ "	 when b.package_type is NULL then 'NA'                                                                                           "
				+ "	 when b.package_type='3' then 'Pet Bottle'                                                                                       "
				+ "	 when b.package_type='4' then 'Tetra Pack' end as package_name,quantity,code_generate_through ,                                  "
				+ "	       c.brewery_nm as vch_undertaking_name,                                                                                     "
				+ "	 'BREWERY' as typee ,d.description  as distrct , d.description  as distrct2 from  distillery.brand_registration_19_20 a,         "
				+ "distillery.packaging_details_19_20 b ,                                                                                              "
				+ "	 public.bre_mst_b1_lic c     ,public.district d                                                                                  "
				+ "	 where a.brand_id=b.brand_id_fk  and a.brewery_id=c.vch_app_id_f::numeric and a.license_category in ('BEER')                     "
				+ "	 and c.int_upkrm_district_id=d.districtid::text   and c.etin_unit_id='"
				+ unitid
				+ "'"
				+ "	 UNION ALL                                                                                                                       "
				+ "		select distinct brand_id_fk,package_id,b.rounded_mrp as mrp,                                                                 "
				+ "		a.brand_name,a.licencee_dtl,a.strength,license_category,                                                                     "
				+ "		license_number, e.vch_indus_name as  manufacturer_details,                                                                   "
				+ "		case when b.package_type='1' then 'Glass Bottle'                                                                             "
				+ "		when b.package_type='2' then 'CAN'                                                                                           "
				+ "		when b.package_type is NULL then 'NA'                                                                                        "
				+ "		when b.package_type='3' then 'Pet Bottle'                                                                                    "
				+ "		when b.package_type='4' then 'Tetra Pack' end as package_name,quantity,code_generate_through ,                               "
				+ "		c.vch_firm_name as vch_undertaking_name,'BWFL' as typee,'NA' as distrct,                                                     "
				+ "		 (select vch_state_name from public.state_ind where int_state_id = e.vch_reg_office_state::int) as distrct2                  "
				+ "		from  distillery.brand_registration_19_20 a,distillery.packaging_details_19_20 b ,                                           "
				+ "		 bwfl.registration_of_bwfl_lic_holder_19_20 c  , public.other_unit_registration e                                            "
				+ "		 where a.brand_id=b.brand_id_fk  and a.int_bwfl_id=c.unit_id  and e.int_app_id_f= c.unit_id and e.vch_indus_type             "
				+ "in ('OUPD','OUPB','OUPW','OUPBU') and a.int_bwfl_id=e.int_app_id_f                                                                  "
				+ "	  UNION ALL                                                                                                                      "
				+ "		 select distinct brand_id_fk,package_id,b.rounded_mrp as mrp,a.brand_name,a.licencee_dtl,a.strength,license_category,        "
				+ "		 license_number, c.vch_indus_name as manufacturer_details,                                                                   "
				+ "		 case when b.package_type='1' then 'Glass Bottle'                                                                            "
				+ "		 when b.package_type='2' then 'CAN'                                                                                          "
				+ "		 when b.package_type is NULL then 'NA'                                                                                       "
				+ "		 when b.package_type='3' then 'Pet Bottle'                                                                                   "
				+ "		 when b.package_type='4' then 'Tetra Pack' end as package_name,quantity,code_generate_through ,                              "
				+ "		 c.vch_indus_name as vch_undertaking_name,                                                                                   "
				+ "		 'FL2D' as typee ,'-' as distrct ,                                                                                           "
				+ "		 (Select                                                                                                                     "
				+ "		 vch_country_name from public.country_mst                                                                                    "
				+ "		 where int_country_id=c.vch_wrk_office_country::int) as distrct2 from  distillery.brand_registration_19_20 a,                "
				+ "		 distillery.packaging_details_19_20 b ,                                                                                      "
				+ "		 public.other_unit_registration c  where  a.brand_id=b.brand_id_fk  and a.int_fl2d_id=c.int_app_id_f   and                   "
				+ "		  c.vch_indus_type='IU'                                                                                                      "
				+ "                                                                                                                                    "
				+ "	  UNION ALL                                                                                                                      "
				+ "	  select distinct brand_id_fk,package_id,b.rounded_mrp as mrp,a.brand_name,a.licencee_dtl,a.strength,license_category,           "
				+ "	  license_number,manufacturer_details,                                                                                           "
				+ "	 	case when b.package_type='1' then 'Glass Bottle'                                                                             "
				+ "	 	when b.package_type='2' then 'CAN'                                                                                           "
				+ "	 	when b.package_type is NULL then 'NA'                                                                                        "
				+ "	 	when b.package_type='3' then 'Pet Bottle'                                                                                    "
				+ "	 	when b.package_type='4' then 'Tetra Pack' end as package_name,quantity,code_generate_through ,                               "
				+ "	       c.vch_indus_name as vch_undertaking_name,                                                                                 "
				+ "	 	'Fl2A' as typee ,d.description as distrct, d.description as distrct2  from  distillery.brand_registration_19_20 a,           "
				+ "	       distillery.packaging_details_19_20 b ,                                                                                    "
				+ "	 	public.other_unit_registration c  left outer join public.district d   on 0=d.districtid                                      "
				+ "	  where a.brand_id=b.brand_id_fk  and a.int_fl2a_id=c.int_app_id_f) x where x.code_generate_through='"
				+ etin + "'";

		JSONObject job_return = new JSONObject();
		JSONArray jary = new JSONArray();
		Connection conn = null;

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn1 = null;
		PreparedStatement pstmt1 = null;
		ResultSet rs1 = null;
		try {
			conn = ConnectionToDataBase.getConnection();
			conn1 = ConnectionToDataBase.getConnection3();
			
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				//JSONObject job = new JSONObject();
			//job.put("brand_name", rs.getString("brand_name"));
				
				//job.put("size", rs.getString("quantity"));
				//job.put("type", rs.getString("typee"));
				//job.put("brand_id", rs.getString("brand_id_fk"));
				//job.put("pkg_id", rs.getString("package_id"));
				
				
				if (rs.getString("typee").equalsIgnoreCase("FL2D")) {
					pstmt1 = conn1.prepareStatement(fl2d);
					rs1 = pstmt1.executeQuery();

					if (rs1.next()) {
						
						responseCaseCode=etin+"1"+caseno.substring(13, 16)+bottledate+getDataBottlingPlan(rs1.getString("plan_id"), "FL2D")+rs1.getString("casecode");
						//job.put("status", "S");

					} else {
						//job.put("status", "N");

					}

					if(responseCaseCode.length()==35)
					{
						//job.put("casecode",responseCaseCode);
					}
					
				}
				if (rs.getString("typee").equalsIgnoreCase("BWFL")) {

					pstmt1 = conn1.prepareStatement(bwfl);
					rs1 = pstmt1.executeQuery();
					if (rs1.next()) {
						responseCaseCode=etin+"1"+caseno.substring(13, 16)+bottledate+getDataBottlingPlan(rs1.getString("plan_id"), "BWFL")+rs1.getString("casecode");
						//job.put("status", "S");

					} else {
						//job.put("status", "N");

					}
					if(responseCaseCode.length()==35)
					{
						//job.put("casecode",responseCaseCode);
					}
				}
				if (rs.getString("typee").equalsIgnoreCase("DISTILLERY")) {

					
					System.out.println("typpeee atul");
					if (licence_type.equals("1")) {
						pstmt1 = conn1.prepareStatement(disliry_fl3);
						System.out.println("typpeee atul1"+disliry_fl3);
					} else if (licence_type.equals("2")) {
						pstmt1 = conn1.prepareStatement(disliry_fl3a);
						System.out.println("typpeee atul2"+disliry_fl3a);
					} else if (licence_type.equals("3")) {
						System.out.println("typpeee atul3"+disliry_cl);
						pstmt1 = conn1.prepareStatement(disliry_cl);
					}

					rs1 = pstmt1.executeQuery();
					if (rs1.next()) {
						System.out.println("hiiiiii");
						responseCaseCode=etin+"1"+caseno.substring(13, 16)+bottledate+getDataBottlingPlan(rs1.getString("plan_id"), "DISTILLERY")+rs1.getString("casecode");
						//job.put("status", "S");

					} else {
						//job.put("status", "N");

					}
					if(responseCaseCode.length()==35)
					{
						//job.put("casecode",responseCaseCode);
					}

				}
				if (rs.getString("typee").equalsIgnoreCase("BREWERY")) {

					if (licence_type.equals("1")) {
						pstmt1 = conn1.prepareStatement(brewary_fl3);
						System.out.println("brewary_fl3 "+brewary_fl3);
					} else if (licence_type.equals("2")) {
						pstmt1 = conn1.prepareStatement(brewary_fl3a);
						System.out.println("brewary_fl3a "+brewary_fl3a);
					}

					rs1 = pstmt1.executeQuery();
					if (rs1.next()) {
						responseCaseCode=etin+"1"+caseno.substring(13, 16)+bottledate+getDataBottlingPlan(rs1.getString("plan_id"), "BREWERY")+rs1.getString("casecode");
						//job.put("status", "S");

					} else {
						//job.put("status", "N");

					}
					if(responseCaseCode.length()==35)
					{
						//job.put("casecode",responseCaseCode);
					}
				}
				if (rs.getString("typee").equalsIgnoreCase("Fl2A")) {
					pstmt1 = conn1.prepareStatement(fl2d);
					rs1 = pstmt1.executeQuery();
					if (rs1.next()) {
						//job.put("status", "S");

					} else {
						//job.put("status", "N");

					}
					
					if(responseCaseCode.length()==35)
					{
						//job.put("casecode",responseCaseCode);
					}
				}
				System.out.println("response case code "+responseCaseCode);
				//job.put("pkg_type", rs.getString("package_name"));

				//jary.put(job);

			} else {
				/*JSONObject job = new JSONObject();
				job.put("brand_name", "NA");
				job.put("casecode", responseCaseCode);
				job.put("size", "0");
				job.put("type", "NA");
				job.put("status", "N");
				job.put("brand_id", "0");
				job.put("pkg_id", "0");
				job.put("pkg_type", "NA");
				jary.put(job);*/
				responseCaseCode="NA";
			}
			//job_return.put("result", jary);
			
			return responseCaseCode;

		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage(), e.getMessage()));
			System.out.println("bottlecode Corresponding Casecode error" + e.fillInStackTrace());
			e.printStackTrace();
			
		} finally {

			try {
				if (conn != null)
					conn.close();
				if (conn1 != null)
					conn1.close();
				if (pstmt != null)
					pstmt.close();
				if (pstmt1 != null)
					pstmt1.close();
				if (rs != null)
					rs.close();
				if (rs1 != null)
					rs1.close();
			} catch (Exception e) {
				e.printStackTrace();
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage(), e.getMessage()));
			}
		}
System.out.println("bottlecode Corresponding Casecode" + job_return);
		System.out.println("job_return" + job_return);
		return responseCaseCode;
		//return "2222responseCaseCode";
	}
	
	
	
	
	public String getDataBottlingPlan(String planId, String type)
	{
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String result="";
		String distillery_sql=
		
		    " SELECT ((case                                                         "+
			" when maped_unmaped_flag='M' then '1'                                  "+
			" when maped_unmaped_flag='A' then '2' end)||                           "+
			" lpad(((int_planned_bottles/int_boxes)::int)::text,3,'0')) as data     "+
			" FROM distillery.mst_bottling_plan_19_20 where seq="+planId;
		
		String brewery_sql=
				" SELECT ((case                                                         "+
						" when maped_unmaped_flag='M' then '1'                                  "+
						" when maped_unmaped_flag='A' then '2' end)||                           "+
						" lpad(((int_planned_bottles/int_boxes)::int)::text,3,'0')) as data     "+
						" FROM bwfl.mst_bottling_plan_19_20 where seq="+planId;
		String bwfl_sql=
				" SELECT ((case                                                         "+
						" when maped_unmaped_flag='M' then '1'                                  "+
						" when maped_unmaped_flag='A' then '2' end)||                           "+
						" lpad(((int_planned_bottles/int_boxes)::int)::text,3,'0')) as data     "+
						" FROM bwfl_license.mst_bottling_plan_19_20 where group_id="+planId;
				
		String fl2d_sql=
				    "select '1'|| lpad(((int_planned_bottles/int_boxes)::int)::text,3,'0') as data "+
				   " from fl2d.mst_stock_receive_19_20 where seq="+planId;
		
		try{
			conn=ConnectionToDataBase.getConnection();
			if(type.equals("DISTILLERY"))
			{
			pstmt=conn.prepareStatement(distillery_sql);
			}else if(type.equals("BREWERY"))
			{
				pstmt=conn.prepareStatement(brewery_sql);
			}else if(type.equals("BWFL"))
			{
				pstmt=conn.prepareStatement(bwfl_sql);
			}else if(type.equals("FL2D"))
			{
				pstmt=conn.prepareStatement(fl2d_sql);
			}
			rs=pstmt.executeQuery();
			if(rs.next())
			{
				result=rs.getString("data");
			}
			
		}catch(Exception e)
		{
			System.out.println(e.fillInStackTrace());
			e.printStackTrace();
		}finally{
			try{
				if(rs!=null)rs.close();
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			}catch(Exception e)
			{
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage(), e.getMessage()));
				System.out.println(e.fillInStackTrace());
				e.printStackTrace();
			}
			
		}
		return result;
	}
	
	
	
	
	
	
	
	
}
