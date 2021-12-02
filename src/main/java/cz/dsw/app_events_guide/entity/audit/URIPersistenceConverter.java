package cz.dsw.app_events_guide.entity.audit;

import org.apache.commons.lang3.StringUtils;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.net.URI;

@Converter(autoApply = true)
public class URIPersistenceConverter implements AttributeConverter<URI, String> {

    @Override
    public String convertToDatabaseColumn(URI uri) {
        return (uri != null) ? uri.toString() : null;
    }

    @Override
    public URI convertToEntityAttribute(String s) {
        return (StringUtils.isNoneBlank(s)) ? URI.create(s.trim()) : null;
    }
}