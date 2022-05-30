package ru.kpfu.sweetlife.converters;

import org.springframework.core.convert.converter.Converter;
import ru.kpfu.sweetlife.exceptions.InvalidParameterException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateConverter implements Converter<String, LocalDate> {

    @Override
    public LocalDate convert(String source) {
        try {
            return LocalDate.parse(source, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        } catch (DateTimeParseException e) {
            throw new InvalidParameterException("Invalid parameter.");
        }
    }
}
