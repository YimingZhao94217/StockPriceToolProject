package com.financial.stockapp;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.financial.dao.StockDao;
import com.financial.pojo.Stock;
import com.financial.pojo.TableCell;
import com.financial.pojo.TableCell.TypeEnum;

@Controller
public class EarningDateController {
	@RequestMapping(value = "earningDate.htm", method = RequestMethod.GET)
	public ModelAndView doService(HttpServletRequest request) throws IOException {
		System.out.println("earning date controller");
		Calendar calendar = Calendar.getInstance(); // select month
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		String monthSelect = request.getParameter("currentMonth");
		String yearSelect = request.getParameter("currentYear");
		String action = request.getParameter("action");
		if (monthSelect != null && yearSelect != null && action != null) {
			Calendar c = Calendar.getInstance();
			request.setAttribute("ban", "none");
			int distance = calculateMonthDistance(Integer.parseInt(yearSelect), Integer.parseInt(monthSelect),
					c.get(Calendar.YEAR), c.get(Calendar.MONTH));
			if (distance >= 12) {
				request.setAttribute("ban", "banNext");
			} else if (distance <= -120) {
				request.setAttribute("ban", "banLast");
			}

			// if (distance < 12 && distance > -120) {
			month = Integer.parseInt(monthSelect);
			if (action.equals("last")) {
				if (month == 0) {
					year = Integer.parseInt(yearSelect) - 1;
					month = 11;
				} else {
					year = Integer.parseInt(yearSelect);
					month = Integer.parseInt(monthSelect) - 1;
				}

			} else {
				if (month == 11) {
					year = Integer.parseInt(yearSelect) + 1;
					month = 0;
				} else {
					year = Integer.parseInt(yearSelect);
					month = Integer.parseInt(monthSelect) + 1;
				}
			}
			calendar.set(Calendar.YEAR, year);
			calendar.set(Calendar.MONTH, month);
			// }

		}

		// calendar.set(Calendar.MONTH, 3);

		// DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		ArrayList<Stock> currentEarnings = (ArrayList<Stock>) StockDao.getInstance().getCurrentEarnings(year, month);
		// for (Stock s : currentEarnings) {
		// System.out.println(s.getSymbol() + ", " +
		// df.format(s.getEarningDates().getStartDate()) + ", "
		// + df.format(s.getEarningDates().getEndDate()));
		// }
		int week = getFirstDayInMonth(year, month).get(Calendar.DAY_OF_WEEK) - 1;
		int listSize = (int) Math.ceil((week + calendar.getActualMaximum(Calendar.DAY_OF_MONTH)) / 7.0);
		List<ArrayList<TableCell>> rev = new ArrayList<ArrayList<TableCell>>();
		Calendar cal; // start month
		if (month == 0)
			cal = getLastWeekendInMonth(year - 1, 11);
		else
			cal = getLastWeekendInMonth(year, month - 1);
		int lastLineNum = 0;
		for (int i = 0; i < listSize; i++) {
			ArrayList<TableCell> firstLine = addRowToRev(rev);
			Calendar sat = Calendar.getInstance(); // become Sat. after looping
			sat.setTimeInMillis(cal.getTimeInMillis());
			// add first line (contains date info)
			for (int j = 0; j < 21; j++) {
				TableCell tc = addTableCell(firstLine);
				if (j == 0 || j % 3 == 0) {
					tc.setType(TypeEnum.date.toString());
					tc.setValue(String.valueOf(sat.get(Calendar.DAY_OF_MONTH)));
					if (j != 18)
						moveToNextDay(sat);
				} else {
					tc.setType(TypeEnum.basicTop.toString());
				}
			}

			// if (currentEarnings != null) {
			// add stock info in first column
			for (Stock s : currentEarnings) {
				if (getDaysFromMilliseconds(s.getEarningDates().getStartDate().getTime()) <= getDaysFromMilliseconds(
						cal.getTimeInMillis())
						&& getDaysFromMilliseconds(
								s.getEarningDates().getEndDate().getTime()) >= getDaysFromMilliseconds(
										cal.getTimeInMillis())) {
					ArrayList<TableCell> line = addRowToRev(rev);
					TableCell tc = addTableCell(line);
					tc.setType(TypeEnum.stock.toString());
					tc.setValue(s.getSymbol());
					int duration = 1
							+ (int) (Math.min(getDaysFromMilliseconds(s.getEarningDates().getEndDate().getTime()),
									getDaysFromMilliseconds(sat.getTimeInMillis()))
									- Math.max(getDaysFromMilliseconds(s.getEarningDates().getStartDate().getTime()),
											getDaysFromMilliseconds(cal.getTimeInMillis())));
					tc.setDuration(duration * 3);
				}
			}
			moveToNextDay(cal);
			// add stock for other columns
			for (int j = 1; j < 7; j++) {
				for (Stock s : currentEarnings) {
					if (getDaysFromMilliseconds(
							s.getEarningDates().getStartDate().getTime()) == getDaysFromMilliseconds(
									cal.getTimeInMillis())) {
						boolean added = false;
						for (int row = lastLineNum + 1; row < rev.size(); row++) { // current
																					// week
																					// group
							int fill = getRestBlock(rev.get(row), j * 3);
							
							if (fill >= 0) {
								ArrayList<TableCell> line = rev.get(row);
								for (int f = 0; f < fill; f++) {
									TableCell tc = addTableCell(line);
									if (f == 0 || f % 3 == 0) {
										tc.setType(TypeEnum.basicLeft.toString());
									} else if (f % 3 == 1) {
										tc.setType(TypeEnum.basic.toString());
									} else
										tc.setType(TypeEnum.basicRight.toString()); // ??don't
																					// need
																					// right?
								}
								// add stock cell
								TableCell tc = addTableCell(line);
								tc.setType(TypeEnum.stock.toString());
								tc.setValue(s.getSymbol());
								int duration = 1 + (int) (Math.min(
										getDaysFromMilliseconds(s.getEarningDates().getEndDate().getTime()),
										getDaysFromMilliseconds(sat.getTimeInMillis()))
										- getDaysFromMilliseconds(cal.getTimeInMillis()));
								tc.setDuration(3 * duration);
								added = true;
								break;
							}
						}
						if (!added) {
							// add stock cell to new line
							ArrayList<TableCell> line = addRowToRev(rev);
							int fill = getRestBlock(line, j * 3);

							for (int f = 0; f < fill; f++) {
								TableCell tc = addTableCell(line);
								if (f == 0 || f % 3 == 0) {
									tc.setType(TypeEnum.basicLeft.toString());
								} else if (f % 3 == 1) {
									tc.setType(TypeEnum.basic.toString());
								} else
									tc.setType(TypeEnum.basicRight.toString()); // ??don't
																				// need
																				// right?
							}
							TableCell tc = addTableCell(line);
							tc.setType(TypeEnum.stock.toString());
							tc.setValue(s.getSymbol());
							int duration = 1 + (int) (Math.min(
									getDaysFromMilliseconds(s.getEarningDates().getEndDate().getTime()),
									getDaysFromMilliseconds(sat.getTimeInMillis()))
									- getDaysFromMilliseconds(cal.getTimeInMillis()));
							tc.setDuration(3 * duration);
						}
					}
				}
				moveToNextDay(cal);
			}
			for (int row = lastLineNum + 1; row < rev.size(); row++) {
				ArrayList<TableCell> line = rev.get(row);
				int fill = getRestBlock(line, 21);
				for (int f = 0; f < fill; f++) {
					TableCell tc = addTableCell(line);
					if (f == 0 || f % 3 == 0) {
						tc.setType(TypeEnum.basicLeft.toString());
					} else if (f % 3 == 1) {
						tc.setType(TypeEnum.basic.toString());
					} else
						tc.setType(TypeEnum.basicRight.toString());
				}
			}
			// }
			// add two line
			int addLine = 3 - rev.size() + lastLineNum;
			for (int row = 0; row < addLine; row++) {
				ArrayList<TableCell> line = addRowToRev(rev);
				for (int col = 0; col < 7; col++) {
					TableCell left = addTableCell(line);
					TableCell mid = addTableCell(line);
					TableCell right = addTableCell(line);
					left.setType(TypeEnum.basicLeft.toString());
					mid.setType(TypeEnum.basic.toString());
					right.setType(TypeEnum.basicRight.toString());
				}
			}
			lastLineNum = rev.size();
		}
		request.setAttribute("calendar", rev);
		request.setAttribute("currentMonth", month);
		request.setAttribute("currentYear", year);
		DateFormat df = new SimpleDateFormat("yyyy-MM");
		request.setAttribute("currentTimeFormat", df.format(calendar.getTime()));
		return new ModelAndView("earningDate");
	}

	private Calendar getFirstDayInMonth(int year, int month) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
		return calendar;
	}

	private Calendar getLastDayInMonth(int year, int month) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		return calendar;
	}

	private Calendar getLastWeekendInMonth(int year, int month) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		calendar.add(Calendar.DAY_OF_MONTH, 1 - calendar.get(Calendar.DAY_OF_WEEK));
		return calendar;
	}

	private ArrayList<TableCell> addRowToRev(List<ArrayList<TableCell>> rev) {
		ArrayList<TableCell> row = new ArrayList<TableCell>();
		rev.add(row);
		return row;
	}

	private TableCell addTableCell(ArrayList<TableCell> row) {
		TableCell tc = new TableCell();
		row.add(tc);
		return tc;
	}

	private int getRestBlock(ArrayList<TableCell> line, int index) {
		int sum = 0;
		for (TableCell tc : line) {
			if (tc.getType().equals(TypeEnum.stock.toString()))
				sum += tc.getDuration();
			else
				sum++;
		}
		return index - sum;
	}

	private Calendar moveToNextDay(Calendar cal) {
		if (cal.get(Calendar.DAY_OF_MONTH) != cal.getActualMaximum(Calendar.DAY_OF_MONTH))
			cal.add(Calendar.DAY_OF_MONTH, 1); // not last day
		else if (cal.get(Calendar.MONTH) != 11) {
			cal.add(Calendar.MONTH, 1); // last day but not last
										// month;
			cal.set(Calendar.DAY_OF_MONTH, 1);
		} else {
			cal.add(Calendar.YEAR, 1); // last day of the year;
			cal.set(Calendar.MONTH, 0);
			cal.set(Calendar.DAY_OF_MONTH, 1);
		}
		return cal;
	}

	private long getDaysFromMilliseconds(long seconds) {
		return Integer.parseInt(String.valueOf(seconds / (1000 * 3600 * 24)));
	}

	private int calculateMonthDistance(int year1, int month1, int year2, int month2) {
		if (month2 > month1) {
			year2--;
			month2 += 12;
		}
		return (year1 - year2) * 12 + (month1 - month2);
	}

}
