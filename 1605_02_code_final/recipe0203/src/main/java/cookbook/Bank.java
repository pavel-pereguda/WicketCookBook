package cookbook;

import java.util.HashMap;
import java.util.Map;

public class Bank
{
	private static Map<String, Integer> balances = new HashMap<String, Integer>();

	public static synchronized int withdraw(String account, int amount)
	{
		initBalance(account);

		int balance = balances.get(account);
		balance -= amount;
		balances.put(account, balance);
		return balance;
	}

	public static synchronized int getBalance(String account)
	{
		initBalance(account);
		return balances.get(account);
	}


	private static void initBalance(String account)
	{
		if (balances.get(account) == null)
		{
			balances.put(account, 1000);
		}
	}
}
