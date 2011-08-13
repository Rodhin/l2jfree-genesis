package com.l2jfree.config.converters;

/**
 * @author NB4L1
 */
public abstract class TypedConverter<T> implements Converter
{
	@Override
	public final Object convertFromString(Class<?> type, String value)
	{
		if (getRequiredType() != type)
			throw new ClassCastException(getRequiredType() + " type required, but found: " + type + "!");
		
		value = (String)DefaultConverter.getInstance().convertFromString(String.class, value);
		
		return convertFromString(value);
	}
	
	@Override
	public final String convertToString(Class<?> type, Object obj)
	{
		if (getRequiredType() != type)
			throw new ClassCastException(getRequiredType() + " type required, but found: " + type + "!");
		
		if (obj == null)
			return "";
		
		if (!getRequiredType().isInstance(obj))
			throw new ClassCastException(getRequiredType() + " value required, but found: '" + obj + "'!");
		
		final String value = convertToString(getRequiredType().cast(obj));
		
		return DefaultConverter.getInstance().convertToString(String.class, value);
	}
	
	protected abstract T convertFromString(String value);
	
	protected abstract String convertToString(T obj);
	
	protected abstract Class<T> getRequiredType();
}
