package cookbook;

import java.io.Serializable;

public class City implements Serializable
{
	private final String code;
	private final String name;

	public City(String code, String name)
	{
		this.code = code;
		this.name = name;
	}

	public String getName()
	{
		return name;
	}

	public String getCode()
	{
		return code;
	}

}
