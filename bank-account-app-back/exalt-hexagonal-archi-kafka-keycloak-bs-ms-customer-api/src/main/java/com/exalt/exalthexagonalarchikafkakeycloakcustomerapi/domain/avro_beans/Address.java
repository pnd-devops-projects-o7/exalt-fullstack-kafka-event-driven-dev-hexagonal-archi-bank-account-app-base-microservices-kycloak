/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package com.exalt.exalthexagonalarchikafkakeycloakcustomerapi.domain.avro_beans;

import org.apache.avro.generic.GenericArray;
import org.apache.avro.specific.SpecificData;
import org.apache.avro.util.Utf8;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.SchemaStore;

@org.apache.avro.specific.AvroGenerated
public class Address extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = 6437663414527349856L;


  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"Address\",\"namespace\":\"com.exalt.exalthexagonalarchikafkakeycloakcustomerapi.domain.avro_beans\",\"fields\":[{\"name\":\"addressId\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"streetNum\",\"type\":\"int\"},{\"name\":\"streetName\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"postalCode\",\"type\":\"int\"},{\"name\":\"city\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"country\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"birthCountry\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static final SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<Address> ENCODER =
      new BinaryMessageEncoder<>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<Address> DECODER =
      new BinaryMessageDecoder<>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageEncoder instance used by this class.
   * @return the message encoder used by this class
   */
  public static BinaryMessageEncoder<Address> getEncoder() {
    return ENCODER;
  }

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   * @return the message decoder used by this class
   */
  public static BinaryMessageDecoder<Address> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   * @return a BinaryMessageDecoder instance for this class backed by the given SchemaStore
   */
  public static BinaryMessageDecoder<Address> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<>(MODEL$, SCHEMA$, resolver);
  }

  /**
   * Serializes this Address to a ByteBuffer.
   * @return a buffer holding the serialized data for this instance
   * @throws java.io.IOException if this instance could not be serialized
   */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /**
   * Deserializes a Address from a ByteBuffer.
   * @param b a byte buffer holding serialized data for an instance of this class
   * @return a Address instance decoded from the given buffer
   * @throws java.io.IOException if the given bytes could not be deserialized into an instance of this class
   */
  public static Address fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

  private java.lang.String addressId;
  private int streetNum;
  private java.lang.String streetName;
  private int postalCode;
  private java.lang.String city;
  private java.lang.String country;
  private java.lang.String birthCountry;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public Address() {}

  /**
   * All-args constructor.
   * @param addressId The new value for addressId
   * @param streetNum The new value for streetNum
   * @param streetName The new value for streetName
   * @param postalCode The new value for postalCode
   * @param city The new value for city
   * @param country The new value for country
   * @param birthCountry The new value for birthCountry
   */
  public Address(java.lang.String addressId, java.lang.Integer streetNum, java.lang.String streetName, java.lang.Integer postalCode, java.lang.String city, java.lang.String country, java.lang.String birthCountry) {
    this.addressId = addressId;
    this.streetNum = streetNum;
    this.streetName = streetName;
    this.postalCode = postalCode;
    this.city = city;
    this.country = country;
    this.birthCountry = birthCountry;
  }

  @Override
  public org.apache.avro.specific.SpecificData getSpecificData() { return MODEL$; }

  @Override
  public org.apache.avro.Schema getSchema() { return SCHEMA$; }

  // Used by DatumWriter.  Applications should not call.
  @Override
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return addressId;
    case 1: return streetNum;
    case 2: return streetName;
    case 3: return postalCode;
    case 4: return city;
    case 5: return country;
    case 6: return birthCountry;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  // Used by DatumReader.  Applications should not call.
  @Override
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: addressId = value$ != null ? value$.toString() : null; break;
    case 1: streetNum = (java.lang.Integer)value$; break;
    case 2: streetName = value$ != null ? value$.toString() : null; break;
    case 3: postalCode = (java.lang.Integer)value$; break;
    case 4: city = value$ != null ? value$.toString() : null; break;
    case 5: country = value$ != null ? value$.toString() : null; break;
    case 6: birthCountry = value$ != null ? value$.toString() : null; break;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  /**
   * Gets the value of the 'addressId' field.
   * @return The value of the 'addressId' field.
   */
  public java.lang.String getAddressId() {
    return addressId;
  }


  /**
   * Sets the value of the 'addressId' field.
   * @param value the value to set.
   */
  public void setAddressId(java.lang.String value) {
    this.addressId = value;
  }

  /**
   * Gets the value of the 'streetNum' field.
   * @return The value of the 'streetNum' field.
   */
  public int getStreetNum() {
    return streetNum;
  }


  /**
   * Sets the value of the 'streetNum' field.
   * @param value the value to set.
   */
  public void setStreetNum(int value) {
    this.streetNum = value;
  }

  /**
   * Gets the value of the 'streetName' field.
   * @return The value of the 'streetName' field.
   */
  public java.lang.String getStreetName() {
    return streetName;
  }


  /**
   * Sets the value of the 'streetName' field.
   * @param value the value to set.
   */
  public void setStreetName(java.lang.String value) {
    this.streetName = value;
  }

  /**
   * Gets the value of the 'postalCode' field.
   * @return The value of the 'postalCode' field.
   */
  public int getPostalCode() {
    return postalCode;
  }


  /**
   * Sets the value of the 'postalCode' field.
   * @param value the value to set.
   */
  public void setPostalCode(int value) {
    this.postalCode = value;
  }

  /**
   * Gets the value of the 'city' field.
   * @return The value of the 'city' field.
   */
  public java.lang.String getCity() {
    return city;
  }


  /**
   * Sets the value of the 'city' field.
   * @param value the value to set.
   */
  public void setCity(java.lang.String value) {
    this.city = value;
  }

  /**
   * Gets the value of the 'country' field.
   * @return The value of the 'country' field.
   */
  public java.lang.String getCountry() {
    return country;
  }


  /**
   * Sets the value of the 'country' field.
   * @param value the value to set.
   */
  public void setCountry(java.lang.String value) {
    this.country = value;
  }

  /**
   * Gets the value of the 'birthCountry' field.
   * @return The value of the 'birthCountry' field.
   */
  public java.lang.String getBirthCountry() {
    return birthCountry;
  }


  /**
   * Sets the value of the 'birthCountry' field.
   * @param value the value to set.
   */
  public void setBirthCountry(java.lang.String value) {
    this.birthCountry = value;
  }

  /**
   * Creates a new Address RecordBuilder.
   * @return A new Address RecordBuilder
   */
  public static com.exalt.exalthexagonalarchikafkakeycloakcustomerapi.domain.avro_beans.Address.Builder newBuilder() {
    return new com.exalt.exalthexagonalarchikafkakeycloakcustomerapi.domain.avro_beans.Address.Builder();
  }

  /**
   * Creates a new Address RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new Address RecordBuilder
   */
  public static com.exalt.exalthexagonalarchikafkakeycloakcustomerapi.domain.avro_beans.Address.Builder newBuilder(com.exalt.exalthexagonalarchikafkakeycloakcustomerapi.domain.avro_beans.Address.Builder other) {
    if (other == null) {
      return new com.exalt.exalthexagonalarchikafkakeycloakcustomerapi.domain.avro_beans.Address.Builder();
    } else {
      return new com.exalt.exalthexagonalarchikafkakeycloakcustomerapi.domain.avro_beans.Address.Builder(other);
    }
  }

  /**
   * Creates a new Address RecordBuilder by copying an existing Address instance.
   * @param other The existing instance to copy.
   * @return A new Address RecordBuilder
   */
  public static com.exalt.exalthexagonalarchikafkakeycloakcustomerapi.domain.avro_beans.Address.Builder newBuilder(com.exalt.exalthexagonalarchikafkakeycloakcustomerapi.domain.avro_beans.Address other) {
    if (other == null) {
      return new com.exalt.exalthexagonalarchikafkakeycloakcustomerapi.domain.avro_beans.Address.Builder();
    } else {
      return new com.exalt.exalthexagonalarchikafkakeycloakcustomerapi.domain.avro_beans.Address.Builder(other);
    }
  }

  /**
   * RecordBuilder for Address instances.
   */
  @org.apache.avro.specific.AvroGenerated
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<Address>
    implements org.apache.avro.data.RecordBuilder<Address> {

    private java.lang.String addressId;
    private int streetNum;
    private java.lang.String streetName;
    private int postalCode;
    private java.lang.String city;
    private java.lang.String country;
    private java.lang.String birthCountry;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$, MODEL$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(com.exalt.exalthexagonalarchikafkakeycloakcustomerapi.domain.avro_beans.Address.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.addressId)) {
        this.addressId = data().deepCopy(fields()[0].schema(), other.addressId);
        fieldSetFlags()[0] = other.fieldSetFlags()[0];
      }
      if (isValidValue(fields()[1], other.streetNum)) {
        this.streetNum = data().deepCopy(fields()[1].schema(), other.streetNum);
        fieldSetFlags()[1] = other.fieldSetFlags()[1];
      }
      if (isValidValue(fields()[2], other.streetName)) {
        this.streetName = data().deepCopy(fields()[2].schema(), other.streetName);
        fieldSetFlags()[2] = other.fieldSetFlags()[2];
      }
      if (isValidValue(fields()[3], other.postalCode)) {
        this.postalCode = data().deepCopy(fields()[3].schema(), other.postalCode);
        fieldSetFlags()[3] = other.fieldSetFlags()[3];
      }
      if (isValidValue(fields()[4], other.city)) {
        this.city = data().deepCopy(fields()[4].schema(), other.city);
        fieldSetFlags()[4] = other.fieldSetFlags()[4];
      }
      if (isValidValue(fields()[5], other.country)) {
        this.country = data().deepCopy(fields()[5].schema(), other.country);
        fieldSetFlags()[5] = other.fieldSetFlags()[5];
      }
      if (isValidValue(fields()[6], other.birthCountry)) {
        this.birthCountry = data().deepCopy(fields()[6].schema(), other.birthCountry);
        fieldSetFlags()[6] = other.fieldSetFlags()[6];
      }
    }

    /**
     * Creates a Builder by copying an existing Address instance
     * @param other The existing instance to copy.
     */
    private Builder(com.exalt.exalthexagonalarchikafkakeycloakcustomerapi.domain.avro_beans.Address other) {
      super(SCHEMA$, MODEL$);
      if (isValidValue(fields()[0], other.addressId)) {
        this.addressId = data().deepCopy(fields()[0].schema(), other.addressId);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.streetNum)) {
        this.streetNum = data().deepCopy(fields()[1].schema(), other.streetNum);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.streetName)) {
        this.streetName = data().deepCopy(fields()[2].schema(), other.streetName);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.postalCode)) {
        this.postalCode = data().deepCopy(fields()[3].schema(), other.postalCode);
        fieldSetFlags()[3] = true;
      }
      if (isValidValue(fields()[4], other.city)) {
        this.city = data().deepCopy(fields()[4].schema(), other.city);
        fieldSetFlags()[4] = true;
      }
      if (isValidValue(fields()[5], other.country)) {
        this.country = data().deepCopy(fields()[5].schema(), other.country);
        fieldSetFlags()[5] = true;
      }
      if (isValidValue(fields()[6], other.birthCountry)) {
        this.birthCountry = data().deepCopy(fields()[6].schema(), other.birthCountry);
        fieldSetFlags()[6] = true;
      }
    }

    /**
      * Gets the value of the 'addressId' field.
      * @return The value.
      */
    public java.lang.String getAddressId() {
      return addressId;
    }


    /**
      * Sets the value of the 'addressId' field.
      * @param value The value of 'addressId'.
      * @return This builder.
      */
    public com.exalt.exalthexagonalarchikafkakeycloakcustomerapi.domain.avro_beans.Address.Builder setAddressId(java.lang.String value) {
      validate(fields()[0], value);
      this.addressId = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'addressId' field has been set.
      * @return True if the 'addressId' field has been set, false otherwise.
      */
    public boolean hasAddressId() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'addressId' field.
      * @return This builder.
      */
    public com.exalt.exalthexagonalarchikafkakeycloakcustomerapi.domain.avro_beans.Address.Builder clearAddressId() {
      addressId = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'streetNum' field.
      * @return The value.
      */
    public int getStreetNum() {
      return streetNum;
    }


    /**
      * Sets the value of the 'streetNum' field.
      * @param value The value of 'streetNum'.
      * @return This builder.
      */
    public com.exalt.exalthexagonalarchikafkakeycloakcustomerapi.domain.avro_beans.Address.Builder setStreetNum(int value) {
      validate(fields()[1], value);
      this.streetNum = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'streetNum' field has been set.
      * @return True if the 'streetNum' field has been set, false otherwise.
      */
    public boolean hasStreetNum() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'streetNum' field.
      * @return This builder.
      */
    public com.exalt.exalthexagonalarchikafkakeycloakcustomerapi.domain.avro_beans.Address.Builder clearStreetNum() {
      fieldSetFlags()[1] = false;
      return this;
    }

    /**
      * Gets the value of the 'streetName' field.
      * @return The value.
      */
    public java.lang.String getStreetName() {
      return streetName;
    }


    /**
      * Sets the value of the 'streetName' field.
      * @param value The value of 'streetName'.
      * @return This builder.
      */
    public com.exalt.exalthexagonalarchikafkakeycloakcustomerapi.domain.avro_beans.Address.Builder setStreetName(java.lang.String value) {
      validate(fields()[2], value);
      this.streetName = value;
      fieldSetFlags()[2] = true;
      return this;
    }

    /**
      * Checks whether the 'streetName' field has been set.
      * @return True if the 'streetName' field has been set, false otherwise.
      */
    public boolean hasStreetName() {
      return fieldSetFlags()[2];
    }


    /**
      * Clears the value of the 'streetName' field.
      * @return This builder.
      */
    public com.exalt.exalthexagonalarchikafkakeycloakcustomerapi.domain.avro_beans.Address.Builder clearStreetName() {
      streetName = null;
      fieldSetFlags()[2] = false;
      return this;
    }

    /**
      * Gets the value of the 'postalCode' field.
      * @return The value.
      */
    public int getPostalCode() {
      return postalCode;
    }


    /**
      * Sets the value of the 'postalCode' field.
      * @param value The value of 'postalCode'.
      * @return This builder.
      */
    public com.exalt.exalthexagonalarchikafkakeycloakcustomerapi.domain.avro_beans.Address.Builder setPostalCode(int value) {
      validate(fields()[3], value);
      this.postalCode = value;
      fieldSetFlags()[3] = true;
      return this;
    }

    /**
      * Checks whether the 'postalCode' field has been set.
      * @return True if the 'postalCode' field has been set, false otherwise.
      */
    public boolean hasPostalCode() {
      return fieldSetFlags()[3];
    }


    /**
      * Clears the value of the 'postalCode' field.
      * @return This builder.
      */
    public com.exalt.exalthexagonalarchikafkakeycloakcustomerapi.domain.avro_beans.Address.Builder clearPostalCode() {
      fieldSetFlags()[3] = false;
      return this;
    }

    /**
      * Gets the value of the 'city' field.
      * @return The value.
      */
    public java.lang.String getCity() {
      return city;
    }


    /**
      * Sets the value of the 'city' field.
      * @param value The value of 'city'.
      * @return This builder.
      */
    public com.exalt.exalthexagonalarchikafkakeycloakcustomerapi.domain.avro_beans.Address.Builder setCity(java.lang.String value) {
      validate(fields()[4], value);
      this.city = value;
      fieldSetFlags()[4] = true;
      return this;
    }

    /**
      * Checks whether the 'city' field has been set.
      * @return True if the 'city' field has been set, false otherwise.
      */
    public boolean hasCity() {
      return fieldSetFlags()[4];
    }


    /**
      * Clears the value of the 'city' field.
      * @return This builder.
      */
    public com.exalt.exalthexagonalarchikafkakeycloakcustomerapi.domain.avro_beans.Address.Builder clearCity() {
      city = null;
      fieldSetFlags()[4] = false;
      return this;
    }

    /**
      * Gets the value of the 'country' field.
      * @return The value.
      */
    public java.lang.String getCountry() {
      return country;
    }


    /**
      * Sets the value of the 'country' field.
      * @param value The value of 'country'.
      * @return This builder.
      */
    public com.exalt.exalthexagonalarchikafkakeycloakcustomerapi.domain.avro_beans.Address.Builder setCountry(java.lang.String value) {
      validate(fields()[5], value);
      this.country = value;
      fieldSetFlags()[5] = true;
      return this;
    }

    /**
      * Checks whether the 'country' field has been set.
      * @return True if the 'country' field has been set, false otherwise.
      */
    public boolean hasCountry() {
      return fieldSetFlags()[5];
    }


    /**
      * Clears the value of the 'country' field.
      * @return This builder.
      */
    public com.exalt.exalthexagonalarchikafkakeycloakcustomerapi.domain.avro_beans.Address.Builder clearCountry() {
      country = null;
      fieldSetFlags()[5] = false;
      return this;
    }

    /**
      * Gets the value of the 'birthCountry' field.
      * @return The value.
      */
    public java.lang.String getBirthCountry() {
      return birthCountry;
    }


    /**
      * Sets the value of the 'birthCountry' field.
      * @param value The value of 'birthCountry'.
      * @return This builder.
      */
    public com.exalt.exalthexagonalarchikafkakeycloakcustomerapi.domain.avro_beans.Address.Builder setBirthCountry(java.lang.String value) {
      validate(fields()[6], value);
      this.birthCountry = value;
      fieldSetFlags()[6] = true;
      return this;
    }

    /**
      * Checks whether the 'birthCountry' field has been set.
      * @return True if the 'birthCountry' field has been set, false otherwise.
      */
    public boolean hasBirthCountry() {
      return fieldSetFlags()[6];
    }


    /**
      * Clears the value of the 'birthCountry' field.
      * @return This builder.
      */
    public com.exalt.exalthexagonalarchikafkakeycloakcustomerapi.domain.avro_beans.Address.Builder clearBirthCountry() {
      birthCountry = null;
      fieldSetFlags()[6] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Address build() {
      try {
        Address record = new Address();
        record.addressId = fieldSetFlags()[0] ? this.addressId : (java.lang.String) defaultValue(fields()[0]);
        record.streetNum = fieldSetFlags()[1] ? this.streetNum : (java.lang.Integer) defaultValue(fields()[1]);
        record.streetName = fieldSetFlags()[2] ? this.streetName : (java.lang.String) defaultValue(fields()[2]);
        record.postalCode = fieldSetFlags()[3] ? this.postalCode : (java.lang.Integer) defaultValue(fields()[3]);
        record.city = fieldSetFlags()[4] ? this.city : (java.lang.String) defaultValue(fields()[4]);
        record.country = fieldSetFlags()[5] ? this.country : (java.lang.String) defaultValue(fields()[5]);
        record.birthCountry = fieldSetFlags()[6] ? this.birthCountry : (java.lang.String) defaultValue(fields()[6]);
        return record;
      } catch (org.apache.avro.AvroMissingFieldException e) {
        throw e;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<Address>
    WRITER$ = (org.apache.avro.io.DatumWriter<Address>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<Address>
    READER$ = (org.apache.avro.io.DatumReader<Address>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

  @Override protected boolean hasCustomCoders() { return true; }

  @Override public void customEncode(org.apache.avro.io.Encoder out)
    throws java.io.IOException
  {
    out.writeString(this.addressId);

    out.writeInt(this.streetNum);

    out.writeString(this.streetName);

    out.writeInt(this.postalCode);

    out.writeString(this.city);

    out.writeString(this.country);

    out.writeString(this.birthCountry);

  }

  @Override public void customDecode(org.apache.avro.io.ResolvingDecoder in)
    throws java.io.IOException
  {
    org.apache.avro.Schema.Field[] fieldOrder = in.readFieldOrderIfDiff();
    if (fieldOrder == null) {
      this.addressId = in.readString();

      this.streetNum = in.readInt();

      this.streetName = in.readString();

      this.postalCode = in.readInt();

      this.city = in.readString();

      this.country = in.readString();

      this.birthCountry = in.readString();

    } else {
      for (int i = 0; i < 7; i++) {
        switch (fieldOrder[i].pos()) {
        case 0:
          this.addressId = in.readString();
          break;

        case 1:
          this.streetNum = in.readInt();
          break;

        case 2:
          this.streetName = in.readString();
          break;

        case 3:
          this.postalCode = in.readInt();
          break;

        case 4:
          this.city = in.readString();
          break;

        case 5:
          this.country = in.readString();
          break;

        case 6:
          this.birthCountry = in.readString();
          break;

        default:
          throw new java.io.IOException("Corrupt ResolvingDecoder.");
        }
      }
    }
  }
}










