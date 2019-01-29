package daoJPA;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.persistence.Converter;
import javax.persistence.AttributeConverter;

@Converter(autoApply = true)
public class LocalDateTimeAttributeConverter implements AttributeConverter<LocalDateTime, Timestamp> {

	@Override
	public Timestamp convertToDatabaseColumn(LocalDateTime locDateTime) {
		// TODO Auto-generated method stub
		return (locDateTime == null ? null : Timestamp.valueOf(locDateTime));
	}

	@Override
	public LocalDateTime convertToEntityAttribute(Timestamp sqlTimestamp) {
		// TODO Auto-generated method stub
		return (sqlTimestamp == null ? null : sqlTimestamp.toLocalDateTime());
	}
}
