package com.xencio.lego.oozie.kit;

import com.xencio.lego.oozie.config.Config;

import java.io.*;
import java.sql.*;
import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;




public class Kit {

	/**
	 * @return Connection
	 */
	public static Connection getHiveConneciton() {

		//new HiveDriver();
		Connection con = null;
		try {
			con = DriverManager.getConnection(config.getHiveAddress(), config.getHiveUser(), config.getHivePsw());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}

	/**
	 * @param con
	 * @param ps
	 * @param rs
	 */
	public static void closeAll(Connection con, PreparedStatement ps, ResultSet rs) {

		try {
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
			if (con != null) {
				con.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param con
	 * @param
	 * @param rs
	 */
	public static void closeAll(Connection con, ResultSet rs, PreparedStatement... psArry) {

		try {
			if (rs != null) {
				rs.close();
			}
			for (PreparedStatement ps : psArry) {
				if (ps != null) {
					ps.close();
				}
			}
			if (con != null) {
				con.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @return
	 */
	public static long getUnixTimeStamp() {

		long currentTimeMillis = System.currentTimeMillis();
		return (long) (currentTimeMillis / 1000);
	}

	public static String getTimePeriodStr(String dataPattern) {

		DateFormat df2 = new SimpleDateFormat(dataPattern);
		String periodStr = df2.format(new Date());
		return periodStr;
	}

	public static String getYesterdayTimestamp() {

		long now = System.currentTimeMillis();
		long yesterday = now - 1000 * 60 * 60 * 24;

		long dbTimestampOfYesterday = (int) (yesterday / 1000);
		return String.valueOf(dbTimestampOfYesterday);
	}

	

	public static String loadSqlText(String sqlTextID) {

		String sqlFilePath = MessageFormat.format(SQL_PATH_PATTERN, sqlTextID);
		InputStream inputStream = null;
		BufferedReader reader = null;
		try {
			inputStream = Kit.class.getResourceAsStream("/" + sqlFilePath);
			if (inputStream == null) {
				inputStream = new FileInputStream(sqlFilePath);
			}

			reader = new BufferedReader(new InputStreamReader(inputStream));

			StringBuilder strBuilder = new StringBuilder("");
			String lineContent = "";
			while ((lineContent = reader.readLine()) != null) {
				strBuilder.append(lineContent + " \r\n");
			}

			String sql = strBuilder.toString();
			System.out.println(sql);
			return sql;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			closeAll(inputStream, reader);
		}

		return "";
	}

	public static void closeAll(InputStream inputStream, BufferedReader reader) {

		try {
			if (reader != null) {
				reader.close();
			}
			if (inputStream != null) {
				inputStream.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static Config config = Config.load();
	private static String SQL_PATH_PATTERN = "{0}.sql";
	private static final String ATTACHMENT_LOCAL_PATH = "attachment/mapping-ext-product-mapping-{0}.csv";
}
