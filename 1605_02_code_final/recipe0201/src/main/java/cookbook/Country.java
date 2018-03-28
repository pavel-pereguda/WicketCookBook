package cookbook;

import java.io.Serializable;

public class Country implements Serializable
{
	private final String code;
	private final String name;

	public Country(String code, String name)
	{
		this.code = code;
		this.name = name;
	}

	public String getCode()
	{
		return code;
	}

	public String getName()
	{
		return name;
	}
}
