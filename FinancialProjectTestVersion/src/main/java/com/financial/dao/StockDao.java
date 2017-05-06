package com.financial.dao;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import com.financial.pojo.Stock;

public class StockDao {
//	private static StockDao stockDao;
	private static Configuration cfg;
	private static SessionFactory sf;
	private Session session;
	private ArrayList<Stock> stockList;

//	public static synchronized StockDao getInstance() {
//		if (stockDao == null)
//			stockDao = new StockDao();
//
//		return stockDao;
//	}

	public StockDao() {
		if(sf == null){
			if(cfg == null)
				cfg = new Configuration();
			sf = cfg.configure("hibernate.cfg.xml").buildSessionFactory();
		}	
	}

	public ArrayList<Stock> getStockList() {
		try {
			if (stockList == null) {
				if (session == null)
					session = sf.openSession();
				String hql = "from Stock";
				Query query = session.createQuery(hql);
				stockList = (ArrayList<Stock>) query.list();
				// session.close();
			}
			return stockList;
		} catch (Exception ex) {
			System.out.println("*** EXCEPTION: " + ex.getMessage());
			return null;
		}
	}

	public ArrayList<Stock> get20MoreStocks(int showed) {
		try {
			if (session == null)
				session = sf.openSession();
			String hql = "from Stock";
			Query query = session.createQuery(hql);
			query.setFirstResult(showed);
			query.setMaxResults(20);
			ArrayList<Stock>rev = (ArrayList<Stock>) query.list();
			// session.close();
			return rev;
		} catch (Exception ex) {
			System.out.println("*** EXCEPTION: " + ex.getMessage());
			return null;
		}
	}

	public void closeSession() {
		if (session != null)
			session.close();
	}

	public Stock getStock(String ticker) {
		Stock stock = null;
		try {
			if (session == null)
				session = sf.openSession();
			String hql = "from Stock stock where stock.symbol = '" + ticker + "'";
			Query query = session.createQuery(hql);
			stock = (Stock) query.uniqueResult();
			return stock;
		} catch (Exception ex) {
			System.out.println("*** EXCEPTION: " + ex.getMessage());
			return null;
		}
	}

	public List<Stock> getCurrentEarnings(int year, int month) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
		Date startDate = calendar.getTime();
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		Date endDate = calendar.getTime();
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		try {
			if (session == null)
				session = sf.openSession();
			String hql = "from Stock stock where stock.earningDates.startDate between '" + formatter.format(startDate)
					+ "' and '" + formatter.format(endDate) + "' or stock.earningDates.endDate between '"
					+ formatter.format(startDate) + "' and '" + formatter.format(endDate) + "'";
			Query query = session.createQuery(hql);
			List<Stock> rev = query.list();
			return rev;
		} catch (Exception ex) {
			System.out.println("*** EXCEPTION: " + ex.getMessage());
			return null;
		}
	}
	
	public List<Stock> searchStock(String input){
		try {
			if (session == null)
				session = sf.openSession();
			String hql = "from Stock stock where stock.symbol like '"+input+"%'";
			Query query = session.createQuery(hql);
			query.setFirstResult(0);
			query.setMaxResults(10);
			List<Stock>rev = query.list();
			if(rev.size() < 10){
				hql = "from Stock stock where stock.symbol like '%" + input + "%'";
				query = session.createQuery(hql);
				query.setFirstResult(0);
				query.setMaxResults(10);
				rev = query.list();
			}
			return rev;
		} catch (Exception ex) {
			System.out.println("*** EXCEPTION: " + ex.getMessage());
			return null;
		}
	}
}
