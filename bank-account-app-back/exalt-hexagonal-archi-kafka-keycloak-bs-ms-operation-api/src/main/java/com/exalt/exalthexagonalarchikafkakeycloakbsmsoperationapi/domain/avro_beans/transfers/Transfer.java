/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.avro_beans.transfers;

import org.apache.avro.generic.GenericArray;
import org.apache.avro.specific.SpecificData;
import org.apache.avro.util.Utf8;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.SchemaStore;

@org.apache.avro.specific.AvroGenerated
public class Transfer extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = 883006264403433382L;


  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"Transfer\",\"namespace\":\"com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.avro_beans.transfers\",\"fields\":[{\"name\":\"transferId\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"originAccount\",\"type\":{\"type\":\"record\",\"name\":\"OriginAccount\",\"namespace\":\"com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.avro_beans.transfers.accounts\",\"fields\":[{\"name\":\"accountId\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"type\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"accountState\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"balance\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\",\"java-class\":\"java.math.BigDecimal\",\"precision\":7,\"scale\":3}},{\"name\":\"createdAt\",\"type\":{\"type\":\"long\",\"logicalType\":\"timestamp-millis\"}},{\"name\":\"overdraft\",\"type\":\"double\"},{\"name\":\"interestRate\",\"type\":\"double\"},{\"name\":\"originCustomer\",\"type\":{\"type\":\"record\",\"name\":\"OriginCustomer\",\"fields\":[{\"name\":\"customerId\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"firstname\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"lastname\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"email\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"customerState\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"createdAt\",\"type\":{\"type\":\"long\",\"logicalType\":\"timestamp-millis\"}}]}}]}},{\"name\":\"destinationAccount\",\"type\":{\"type\":\"record\",\"name\":\"DestinationAccount\",\"namespace\":\"com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.avro_beans.transfers.accounts\",\"fields\":[{\"name\":\"accountId\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"type\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"accountState\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"balance\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\",\"java-class\":\"java.math.BigDecimal\",\"precision\":7,\"scale\":3}},{\"name\":\"createdAt\",\"type\":{\"type\":\"long\",\"logicalType\":\"timestamp-millis\"}},{\"name\":\"overdraft\",\"type\":\"double\"},{\"name\":\"interestRate\",\"type\":\"double\"},{\"name\":\"destinationCustomer\",\"type\":{\"type\":\"record\",\"name\":\"DestinationCustomer\",\"fields\":[{\"name\":\"customerId\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"firstname\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"lastname\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"email\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"customerState\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"createdAt\",\"type\":{\"type\":\"long\",\"logicalType\":\"timestamp-millis\"}}]}}]}},{\"name\":\"transferAmount\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\",\"java-class\":\"java.math.BigDecimal\",\"precision\":7,\"scale\":3}},{\"name\":\"description\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"createdAt\",\"type\":{\"type\":\"long\",\"logicalType\":\"timestamp-millis\"}}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static final SpecificData MODEL$ = new SpecificData();
  static {
    MODEL$.addLogicalTypeConversion(new org.apache.avro.data.TimeConversions.TimestampMillisConversion());
  }

  private static final BinaryMessageEncoder<Transfer> ENCODER =
      new BinaryMessageEncoder<>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<Transfer> DECODER =
      new BinaryMessageDecoder<>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageEncoder instance used by this class.
   * @return the message encoder used by this class
   */
  public static BinaryMessageEncoder<Transfer> getEncoder() {
    return ENCODER;
  }

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   * @return the message decoder used by this class
   */
  public static BinaryMessageDecoder<Transfer> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   * @return a BinaryMessageDecoder instance for this class backed by the given SchemaStore
   */
  public static BinaryMessageDecoder<Transfer> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<>(MODEL$, SCHEMA$, resolver);
  }

  /**
   * Serializes this Transfer to a ByteBuffer.
   * @return a buffer holding the serialized data for this instance
   * @throws java.io.IOException if this instance could not be serialized
   */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /**
   * Deserializes a Transfer from a ByteBuffer.
   * @param b a byte buffer holding serialized data for an instance of this class
   * @return a Transfer instance decoded from the given buffer
   * @throws java.io.IOException if the given bytes could not be deserialized into an instance of this class
   */
  public static Transfer fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

  private java.lang.String transferId;
  private com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.avro_beans.transfers.accounts.OriginAccount originAccount;
  private com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.avro_beans.transfers.accounts.DestinationAccount destinationAccount;
  private java.math.BigDecimal transferAmount;
  private java.lang.String description;
  private java.time.Instant createdAt;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public Transfer() {}

  /**
   * All-args constructor.
   * @param transferId The new value for transferId
   * @param originAccount The new value for originAccount
   * @param destinationAccount The new value for destinationAccount
   * @param transferAmount The new value for transferAmount
   * @param description The new value for description
   * @param createdAt The new value for createdAt
   */
  public Transfer(java.lang.String transferId, com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.avro_beans.transfers.accounts.OriginAccount originAccount, com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.avro_beans.transfers.accounts.DestinationAccount destinationAccount, java.math.BigDecimal transferAmount, java.lang.String description, java.time.Instant createdAt) {
    this.transferId = transferId;
    this.originAccount = originAccount;
    this.destinationAccount = destinationAccount;
    this.transferAmount = transferAmount;
    this.description = description;
    this.createdAt = createdAt.truncatedTo(java.time.temporal.ChronoUnit.MILLIS);
  }

  @Override
  public org.apache.avro.specific.SpecificData getSpecificData() { return MODEL$; }

  @Override
  public org.apache.avro.Schema getSchema() { return SCHEMA$; }

  // Used by DatumWriter.  Applications should not call.
  @Override
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return transferId;
    case 1: return originAccount;
    case 2: return destinationAccount;
    case 3: return transferAmount;
    case 4: return description;
    case 5: return createdAt;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  private static final org.apache.avro.Conversion<?>[] conversions =
      new org.apache.avro.Conversion<?>[] {
      null,
      null,
      null,
      null,
      null,
      new org.apache.avro.data.TimeConversions.TimestampMillisConversion(),
      null
  };

  @Override
  public org.apache.avro.Conversion<?> getConversion(int field) {
    return conversions[field];
  }

  // Used by DatumReader.  Applications should not call.
  @Override
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: transferId = value$ != null ? value$.toString() : null; break;
    case 1: originAccount = (com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.avro_beans.transfers.accounts.OriginAccount)value$; break;
    case 2: destinationAccount = (com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.avro_beans.transfers.accounts.DestinationAccount)value$; break;
    case 3: transferAmount = (java.math.BigDecimal)value$; break;
    case 4: description = value$ != null ? value$.toString() : null; break;
    case 5: createdAt = (java.time.Instant)value$; break;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  /**
   * Gets the value of the 'transferId' field.
   * @return The value of the 'transferId' field.
   */
  public java.lang.String getTransferId() {
    return transferId;
  }


  /**
   * Sets the value of the 'transferId' field.
   * @param value the value to set.
   */
  public void setTransferId(java.lang.String value) {
    this.transferId = value;
  }

  /**
   * Gets the value of the 'originAccount' field.
   * @return The value of the 'originAccount' field.
   */
  public com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.avro_beans.transfers.accounts.OriginAccount getOriginAccount() {
    return originAccount;
  }


  /**
   * Sets the value of the 'originAccount' field.
   * @param value the value to set.
   */
  public void setOriginAccount(com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.avro_beans.transfers.accounts.OriginAccount value) {
    this.originAccount = value;
  }

  /**
   * Gets the value of the 'destinationAccount' field.
   * @return The value of the 'destinationAccount' field.
   */
  public com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.avro_beans.transfers.accounts.DestinationAccount getDestinationAccount() {
    return destinationAccount;
  }


  /**
   * Sets the value of the 'destinationAccount' field.
   * @param value the value to set.
   */
  public void setDestinationAccount(com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.avro_beans.transfers.accounts.DestinationAccount value) {
    this.destinationAccount = value;
  }

  /**
   * Gets the value of the 'transferAmount' field.
   * @return The value of the 'transferAmount' field.
   */
  public java.math.BigDecimal getTransferAmount() {
    return transferAmount;
  }


  /**
   * Sets the value of the 'transferAmount' field.
   * @param value the value to set.
   */
  public void setTransferAmount(java.math.BigDecimal value) {
    this.transferAmount = value;
  }

  /**
   * Gets the value of the 'description' field.
   * @return The value of the 'description' field.
   */
  public java.lang.String getDescription() {
    return description;
  }


  /**
   * Sets the value of the 'description' field.
   * @param value the value to set.
   */
  public void setDescription(java.lang.String value) {
    this.description = value;
  }

  /**
   * Gets the value of the 'createdAt' field.
   * @return The value of the 'createdAt' field.
   */
  public java.time.Instant getCreatedAt() {
    return createdAt;
  }


  /**
   * Sets the value of the 'createdAt' field.
   * @param value the value to set.
   */
  public void setCreatedAt(java.time.Instant value) {
    this.createdAt = value.truncatedTo(java.time.temporal.ChronoUnit.MILLIS);
  }

  /**
   * Creates a new Transfer RecordBuilder.
   * @return A new Transfer RecordBuilder
   */
  public static com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.avro_beans.transfers.Transfer.Builder newBuilder() {
    return new com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.avro_beans.transfers.Transfer.Builder();
  }

  /**
   * Creates a new Transfer RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new Transfer RecordBuilder
   */
  public static com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.avro_beans.transfers.Transfer.Builder newBuilder(com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.avro_beans.transfers.Transfer.Builder other) {
    if (other == null) {
      return new com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.avro_beans.transfers.Transfer.Builder();
    } else {
      return new com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.avro_beans.transfers.Transfer.Builder(other);
    }
  }

  /**
   * Creates a new Transfer RecordBuilder by copying an existing Transfer instance.
   * @param other The existing instance to copy.
   * @return A new Transfer RecordBuilder
   */
  public static com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.avro_beans.transfers.Transfer.Builder newBuilder(com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.avro_beans.transfers.Transfer other) {
    if (other == null) {
      return new com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.avro_beans.transfers.Transfer.Builder();
    } else {
      return new com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.avro_beans.transfers.Transfer.Builder(other);
    }
  }

  /**
   * RecordBuilder for Transfer instances.
   */
  @org.apache.avro.specific.AvroGenerated
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<Transfer>
    implements org.apache.avro.data.RecordBuilder<Transfer> {

    private java.lang.String transferId;
    private com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.avro_beans.transfers.accounts.OriginAccount originAccount;
    private com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.avro_beans.transfers.accounts.OriginAccount.Builder originAccountBuilder;
    private com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.avro_beans.transfers.accounts.DestinationAccount destinationAccount;
    private com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.avro_beans.transfers.accounts.DestinationAccount.Builder destinationAccountBuilder;
    private java.math.BigDecimal transferAmount;
    private java.lang.String description;
    private java.time.Instant createdAt;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$, MODEL$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.avro_beans.transfers.Transfer.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.transferId)) {
        this.transferId = data().deepCopy(fields()[0].schema(), other.transferId);
        fieldSetFlags()[0] = other.fieldSetFlags()[0];
      }
      if (isValidValue(fields()[1], other.originAccount)) {
        this.originAccount = data().deepCopy(fields()[1].schema(), other.originAccount);
        fieldSetFlags()[1] = other.fieldSetFlags()[1];
      }
      if (other.hasOriginAccountBuilder()) {
        this.originAccountBuilder = com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.avro_beans.transfers.accounts.OriginAccount.newBuilder(other.getOriginAccountBuilder());
      }
      if (isValidValue(fields()[2], other.destinationAccount)) {
        this.destinationAccount = data().deepCopy(fields()[2].schema(), other.destinationAccount);
        fieldSetFlags()[2] = other.fieldSetFlags()[2];
      }
      if (other.hasDestinationAccountBuilder()) {
        this.destinationAccountBuilder = com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.avro_beans.transfers.accounts.DestinationAccount.newBuilder(other.getDestinationAccountBuilder());
      }
      if (isValidValue(fields()[3], other.transferAmount)) {
        this.transferAmount = data().deepCopy(fields()[3].schema(), other.transferAmount);
        fieldSetFlags()[3] = other.fieldSetFlags()[3];
      }
      if (isValidValue(fields()[4], other.description)) {
        this.description = data().deepCopy(fields()[4].schema(), other.description);
        fieldSetFlags()[4] = other.fieldSetFlags()[4];
      }
      if (isValidValue(fields()[5], other.createdAt)) {
        this.createdAt = data().deepCopy(fields()[5].schema(), other.createdAt);
        fieldSetFlags()[5] = other.fieldSetFlags()[5];
      }
    }

    /**
     * Creates a Builder by copying an existing Transfer instance
     * @param other The existing instance to copy.
     */
    private Builder(com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.avro_beans.transfers.Transfer other) {
      super(SCHEMA$, MODEL$);
      if (isValidValue(fields()[0], other.transferId)) {
        this.transferId = data().deepCopy(fields()[0].schema(), other.transferId);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.originAccount)) {
        this.originAccount = data().deepCopy(fields()[1].schema(), other.originAccount);
        fieldSetFlags()[1] = true;
      }
      this.originAccountBuilder = null;
      if (isValidValue(fields()[2], other.destinationAccount)) {
        this.destinationAccount = data().deepCopy(fields()[2].schema(), other.destinationAccount);
        fieldSetFlags()[2] = true;
      }
      this.destinationAccountBuilder = null;
      if (isValidValue(fields()[3], other.transferAmount)) {
        this.transferAmount = data().deepCopy(fields()[3].schema(), other.transferAmount);
        fieldSetFlags()[3] = true;
      }
      if (isValidValue(fields()[4], other.description)) {
        this.description = data().deepCopy(fields()[4].schema(), other.description);
        fieldSetFlags()[4] = true;
      }
      if (isValidValue(fields()[5], other.createdAt)) {
        this.createdAt = data().deepCopy(fields()[5].schema(), other.createdAt);
        fieldSetFlags()[5] = true;
      }
    }

    /**
      * Gets the value of the 'transferId' field.
      * @return The value.
      */
    public java.lang.String getTransferId() {
      return transferId;
    }


    /**
      * Sets the value of the 'transferId' field.
      * @param value The value of 'transferId'.
      * @return This builder.
      */
    public com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.avro_beans.transfers.Transfer.Builder setTransferId(java.lang.String value) {
      validate(fields()[0], value);
      this.transferId = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'transferId' field has been set.
      * @return True if the 'transferId' field has been set, false otherwise.
      */
    public boolean hasTransferId() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'transferId' field.
      * @return This builder.
      */
    public com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.avro_beans.transfers.Transfer.Builder clearTransferId() {
      transferId = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'originAccount' field.
      * @return The value.
      */
    public com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.avro_beans.transfers.accounts.OriginAccount getOriginAccount() {
      return originAccount;
    }


    /**
      * Sets the value of the 'originAccount' field.
      * @param value The value of 'originAccount'.
      * @return This builder.
      */
    public com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.avro_beans.transfers.Transfer.Builder setOriginAccount(com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.avro_beans.transfers.accounts.OriginAccount value) {
      validate(fields()[1], value);
      this.originAccountBuilder = null;
      this.originAccount = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'originAccount' field has been set.
      * @return True if the 'originAccount' field has been set, false otherwise.
      */
    public boolean hasOriginAccount() {
      return fieldSetFlags()[1];
    }

    /**
     * Gets the Builder instance for the 'originAccount' field and creates one if it doesn't exist yet.
     * @return This builder.
     */
    public com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.avro_beans.transfers.accounts.OriginAccount.Builder getOriginAccountBuilder() {
      if (originAccountBuilder == null) {
        if (hasOriginAccount()) {
          setOriginAccountBuilder(com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.avro_beans.transfers.accounts.OriginAccount.newBuilder(originAccount));
        } else {
          setOriginAccountBuilder(com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.avro_beans.transfers.accounts.OriginAccount.newBuilder());
        }
      }
      return originAccountBuilder;
    }

    /**
     * Sets the Builder instance for the 'originAccount' field
     * @param value The builder instance that must be set.
     * @return This builder.
     */

    public com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.avro_beans.transfers.Transfer.Builder setOriginAccountBuilder(com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.avro_beans.transfers.accounts.OriginAccount.Builder value) {
      clearOriginAccount();
      originAccountBuilder = value;
      return this;
    }

    /**
     * Checks whether the 'originAccount' field has an active Builder instance
     * @return True if the 'originAccount' field has an active Builder instance
     */
    public boolean hasOriginAccountBuilder() {
      return originAccountBuilder != null;
    }

    /**
      * Clears the value of the 'originAccount' field.
      * @return This builder.
      */
    public com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.avro_beans.transfers.Transfer.Builder clearOriginAccount() {
      originAccount = null;
      originAccountBuilder = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    /**
      * Gets the value of the 'destinationAccount' field.
      * @return The value.
      */
    public com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.avro_beans.transfers.accounts.DestinationAccount getDestinationAccount() {
      return destinationAccount;
    }


    /**
      * Sets the value of the 'destinationAccount' field.
      * @param value The value of 'destinationAccount'.
      * @return This builder.
      */
    public com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.avro_beans.transfers.Transfer.Builder setDestinationAccount(com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.avro_beans.transfers.accounts.DestinationAccount value) {
      validate(fields()[2], value);
      this.destinationAccountBuilder = null;
      this.destinationAccount = value;
      fieldSetFlags()[2] = true;
      return this;
    }

    /**
      * Checks whether the 'destinationAccount' field has been set.
      * @return True if the 'destinationAccount' field has been set, false otherwise.
      */
    public boolean hasDestinationAccount() {
      return fieldSetFlags()[2];
    }

    /**
     * Gets the Builder instance for the 'destinationAccount' field and creates one if it doesn't exist yet.
     * @return This builder.
     */
    public com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.avro_beans.transfers.accounts.DestinationAccount.Builder getDestinationAccountBuilder() {
      if (destinationAccountBuilder == null) {
        if (hasDestinationAccount()) {
          setDestinationAccountBuilder(com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.avro_beans.transfers.accounts.DestinationAccount.newBuilder(destinationAccount));
        } else {
          setDestinationAccountBuilder(com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.avro_beans.transfers.accounts.DestinationAccount.newBuilder());
        }
      }
      return destinationAccountBuilder;
    }

    /**
     * Sets the Builder instance for the 'destinationAccount' field
     * @param value The builder instance that must be set.
     * @return This builder.
     */

    public com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.avro_beans.transfers.Transfer.Builder setDestinationAccountBuilder(com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.avro_beans.transfers.accounts.DestinationAccount.Builder value) {
      clearDestinationAccount();
      destinationAccountBuilder = value;
      return this;
    }

    /**
     * Checks whether the 'destinationAccount' field has an active Builder instance
     * @return True if the 'destinationAccount' field has an active Builder instance
     */
    public boolean hasDestinationAccountBuilder() {
      return destinationAccountBuilder != null;
    }

    /**
      * Clears the value of the 'destinationAccount' field.
      * @return This builder.
      */
    public com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.avro_beans.transfers.Transfer.Builder clearDestinationAccount() {
      destinationAccount = null;
      destinationAccountBuilder = null;
      fieldSetFlags()[2] = false;
      return this;
    }

    /**
      * Gets the value of the 'transferAmount' field.
      * @return The value.
      */
    public java.math.BigDecimal getTransferAmount() {
      return transferAmount;
    }


    /**
      * Sets the value of the 'transferAmount' field.
      * @param value The value of 'transferAmount'.
      * @return This builder.
      */
    public com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.avro_beans.transfers.Transfer.Builder setTransferAmount(java.math.BigDecimal value) {
      validate(fields()[3], value);
      this.transferAmount = value;
      fieldSetFlags()[3] = true;
      return this;
    }

    /**
      * Checks whether the 'transferAmount' field has been set.
      * @return True if the 'transferAmount' field has been set, false otherwise.
      */
    public boolean hasTransferAmount() {
      return fieldSetFlags()[3];
    }


    /**
      * Clears the value of the 'transferAmount' field.
      * @return This builder.
      */
    public com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.avro_beans.transfers.Transfer.Builder clearTransferAmount() {
      transferAmount = null;
      fieldSetFlags()[3] = false;
      return this;
    }

    /**
      * Gets the value of the 'description' field.
      * @return The value.
      */
    public java.lang.String getDescription() {
      return description;
    }


    /**
      * Sets the value of the 'description' field.
      * @param value The value of 'description'.
      * @return This builder.
      */
    public com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.avro_beans.transfers.Transfer.Builder setDescription(java.lang.String value) {
      validate(fields()[4], value);
      this.description = value;
      fieldSetFlags()[4] = true;
      return this;
    }

    /**
      * Checks whether the 'description' field has been set.
      * @return True if the 'description' field has been set, false otherwise.
      */
    public boolean hasDescription() {
      return fieldSetFlags()[4];
    }


    /**
      * Clears the value of the 'description' field.
      * @return This builder.
      */
    public com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.avro_beans.transfers.Transfer.Builder clearDescription() {
      description = null;
      fieldSetFlags()[4] = false;
      return this;
    }

    /**
      * Gets the value of the 'createdAt' field.
      * @return The value.
      */
    public java.time.Instant getCreatedAt() {
      return createdAt;
    }


    /**
      * Sets the value of the 'createdAt' field.
      * @param value The value of 'createdAt'.
      * @return This builder.
      */
    public com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.avro_beans.transfers.Transfer.Builder setCreatedAt(java.time.Instant value) {
      validate(fields()[5], value);
      this.createdAt = value.truncatedTo(java.time.temporal.ChronoUnit.MILLIS);
      fieldSetFlags()[5] = true;
      return this;
    }

    /**
      * Checks whether the 'createdAt' field has been set.
      * @return True if the 'createdAt' field has been set, false otherwise.
      */
    public boolean hasCreatedAt() {
      return fieldSetFlags()[5];
    }


    /**
      * Clears the value of the 'createdAt' field.
      * @return This builder.
      */
    public com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.avro_beans.transfers.Transfer.Builder clearCreatedAt() {
      fieldSetFlags()[5] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Transfer build() {
      try {
        Transfer record = new Transfer();
        record.transferId = fieldSetFlags()[0] ? this.transferId : (java.lang.String) defaultValue(fields()[0]);
        if (originAccountBuilder != null) {
          try {
            record.originAccount = this.originAccountBuilder.build();
          } catch (org.apache.avro.AvroMissingFieldException e) {
            e.addParentField(record.getSchema().getField("originAccount"));
            throw e;
          }
        } else {
          record.originAccount = fieldSetFlags()[1] ? this.originAccount : (com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.avro_beans.transfers.accounts.OriginAccount) defaultValue(fields()[1]);
        }
        if (destinationAccountBuilder != null) {
          try {
            record.destinationAccount = this.destinationAccountBuilder.build();
          } catch (org.apache.avro.AvroMissingFieldException e) {
            e.addParentField(record.getSchema().getField("destinationAccount"));
            throw e;
          }
        } else {
          record.destinationAccount = fieldSetFlags()[2] ? this.destinationAccount : (com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.avro_beans.transfers.accounts.DestinationAccount) defaultValue(fields()[2]);
        }
        record.transferAmount = fieldSetFlags()[3] ? this.transferAmount : (java.math.BigDecimal) defaultValue(fields()[3]);
        record.description = fieldSetFlags()[4] ? this.description : (java.lang.String) defaultValue(fields()[4]);
        record.createdAt = fieldSetFlags()[5] ? this.createdAt : (java.time.Instant) defaultValue(fields()[5]);
        return record;
      } catch (org.apache.avro.AvroMissingFieldException e) {
        throw e;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<Transfer>
    WRITER$ = (org.apache.avro.io.DatumWriter<Transfer>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<Transfer>
    READER$ = (org.apache.avro.io.DatumReader<Transfer>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

}










