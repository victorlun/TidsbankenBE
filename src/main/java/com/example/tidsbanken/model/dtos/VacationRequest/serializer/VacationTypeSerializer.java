package com.example.tidsbanken.model.dtos.VacationRequest.serializer;
import com.example.tidsbanken.enumerator.VacationType;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;

public class VacationTypeSerializer extends JsonSerializer<VacationType> {
    @Override
    public void serialize(VacationType value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeString(value.getDisplayValue());
    }
}
