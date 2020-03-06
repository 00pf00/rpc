// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: DeviceInfo.proto

package cn.bupt.edu.entity;

public final class DeviceInfoProto {
  private DeviceInfoProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  public interface DeviceInfoOrBuilder extends
      // @@protoc_insertion_point(interface_extends:DeviceInfo)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>string id = 1;</code>
     * @return The id.
     */
    String getId();
    /**
     * <code>string id = 1;</code>
     * @return The bytes for id.
     */
    com.google.protobuf.ByteString
        getIdBytes();

    /**
     * <code>string name = 2;</code>
     * @return The name.
     */
    String getName();
    /**
     * <code>string name = 2;</code>
     * @return The bytes for name.
     */
    com.google.protobuf.ByteString
        getNameBytes();

    /**
     * <code>int32 temperature = 3;</code>
     * @return The temperature.
     */
    int getTemperature();
  }
  /**
   * Protobuf type {@code DeviceInfo}
   */
  public  static final class DeviceInfo extends
      com.google.protobuf.GeneratedMessageV3 implements
      // @@protoc_insertion_point(message_implements:DeviceInfo)
      DeviceInfoOrBuilder {
  private static final long serialVersionUID = 0L;
    // Use DeviceInfo.newBuilder() to construct.
    private DeviceInfo(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
      super(builder);
    }
    private DeviceInfo() {
      id_ = "";
      name_ = "";
    }

    @Override
    @SuppressWarnings({"unused"})
    protected Object newInstance(
        UnusedPrivateParameter unused) {
      return new DeviceInfo();
    }

    @Override
    public final com.google.protobuf.UnknownFieldSet
    getUnknownFields() {
      return this.unknownFields;
    }
    private DeviceInfo(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      this();
      if (extensionRegistry == null) {
        throw new NullPointerException();
      }
      com.google.protobuf.UnknownFieldSet.Builder unknownFields =
          com.google.protobuf.UnknownFieldSet.newBuilder();
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            case 10: {
              String s = input.readStringRequireUtf8();

              id_ = s;
              break;
            }
            case 18: {
              String s = input.readStringRequireUtf8();

              name_ = s;
              break;
            }
            case 24: {

              temperature_ = input.readInt32();
              break;
            }
            default: {
              if (!parseUnknownField(
                  input, unknownFields, extensionRegistry, tag)) {
                done = true;
              }
              break;
            }
          }
        }
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(this);
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(
            e).setUnfinishedMessage(this);
      } finally {
        this.unknownFields = unknownFields.build();
        makeExtensionsImmutable();
      }
    }
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return cn.bupt.edu.entity.DeviceInfoProto.internal_static_DeviceInfo_descriptor;
    }

    @Override
    protected FieldAccessorTable
        internalGetFieldAccessorTable() {
      return cn.bupt.edu.entity.DeviceInfoProto.internal_static_DeviceInfo_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              cn.bupt.edu.entity.DeviceInfoProto.DeviceInfo.class, cn.bupt.edu.entity.DeviceInfoProto.DeviceInfo.Builder.class);
    }

    public static final int ID_FIELD_NUMBER = 1;
    private volatile Object id_;
    /**
     * <code>string id = 1;</code>
     * @return The id.
     */
    public String getId() {
      Object ref = id_;
      if (ref instanceof String) {
        return (String) ref;
      } else {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        String s = bs.toStringUtf8();
        id_ = s;
        return s;
      }
    }
    /**
     * <code>string id = 1;</code>
     * @return The bytes for id.
     */
    public com.google.protobuf.ByteString
        getIdBytes() {
      Object ref = id_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b =
            com.google.protobuf.ByteString.copyFromUtf8(
                (String) ref);
        id_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    public static final int NAME_FIELD_NUMBER = 2;
    private volatile Object name_;
    /**
     * <code>string name = 2;</code>
     * @return The name.
     */
    public String getName() {
      Object ref = name_;
      if (ref instanceof String) {
        return (String) ref;
      } else {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        String s = bs.toStringUtf8();
        name_ = s;
        return s;
      }
    }
    /**
     * <code>string name = 2;</code>
     * @return The bytes for name.
     */
    public com.google.protobuf.ByteString
        getNameBytes() {
      Object ref = name_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b =
            com.google.protobuf.ByteString.copyFromUtf8(
                (String) ref);
        name_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    public static final int TEMPERATURE_FIELD_NUMBER = 3;
    private int temperature_;
    /**
     * <code>int32 temperature = 3;</code>
     * @return The temperature.
     */
    public int getTemperature() {
      return temperature_;
    }

    private byte memoizedIsInitialized = -1;
    @Override
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized == 1) return true;
      if (isInitialized == 0) return false;

      memoizedIsInitialized = 1;
      return true;
    }

    @Override
    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      if (!getIdBytes().isEmpty()) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 1, id_);
      }
      if (!getNameBytes().isEmpty()) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 2, name_);
      }
      if (temperature_ != 0) {
        output.writeInt32(3, temperature_);
      }
      unknownFields.writeTo(output);
    }

    @Override
    public int getSerializedSize() {
      int size = memoizedSize;
      if (size != -1) return size;

      size = 0;
      if (!getIdBytes().isEmpty()) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, id_);
      }
      if (!getNameBytes().isEmpty()) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, name_);
      }
      if (temperature_ != 0) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(3, temperature_);
      }
      size += unknownFields.getSerializedSize();
      memoizedSize = size;
      return size;
    }

    @Override
    public boolean equals(final Object obj) {
      if (obj == this) {
       return true;
      }
      if (!(obj instanceof cn.bupt.edu.entity.DeviceInfoProto.DeviceInfo)) {
        return super.equals(obj);
      }
      cn.bupt.edu.entity.DeviceInfoProto.DeviceInfo other = (cn.bupt.edu.entity.DeviceInfoProto.DeviceInfo) obj;

      if (!getId()
          .equals(other.getId())) return false;
      if (!getName()
          .equals(other.getName())) return false;
      if (getTemperature()
          != other.getTemperature()) return false;
      if (!unknownFields.equals(other.unknownFields)) return false;
      return true;
    }

    @Override
    public int hashCode() {
      if (memoizedHashCode != 0) {
        return memoizedHashCode;
      }
      int hash = 41;
      hash = (19 * hash) + getDescriptor().hashCode();
      hash = (37 * hash) + ID_FIELD_NUMBER;
      hash = (53 * hash) + getId().hashCode();
      hash = (37 * hash) + NAME_FIELD_NUMBER;
      hash = (53 * hash) + getName().hashCode();
      hash = (37 * hash) + TEMPERATURE_FIELD_NUMBER;
      hash = (53 * hash) + getTemperature();
      hash = (29 * hash) + unknownFields.hashCode();
      memoizedHashCode = hash;
      return hash;
    }

    public static cn.bupt.edu.entity.DeviceInfoProto.DeviceInfo parseFrom(
        java.nio.ByteBuffer data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static cn.bupt.edu.entity.DeviceInfoProto.DeviceInfo parseFrom(
        java.nio.ByteBuffer data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static cn.bupt.edu.entity.DeviceInfoProto.DeviceInfo parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static cn.bupt.edu.entity.DeviceInfoProto.DeviceInfo parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static cn.bupt.edu.entity.DeviceInfoProto.DeviceInfo parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static cn.bupt.edu.entity.DeviceInfoProto.DeviceInfo parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static cn.bupt.edu.entity.DeviceInfoProto.DeviceInfo parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static cn.bupt.edu.entity.DeviceInfoProto.DeviceInfo parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }
    public static cn.bupt.edu.entity.DeviceInfoProto.DeviceInfo parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input);
    }
    public static cn.bupt.edu.entity.DeviceInfoProto.DeviceInfo parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    public static cn.bupt.edu.entity.DeviceInfoProto.DeviceInfo parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static cn.bupt.edu.entity.DeviceInfoProto.DeviceInfo parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }

    @Override
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    public static Builder newBuilder(cn.bupt.edu.entity.DeviceInfoProto.DeviceInfo prototype) {
      return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
    }
    @Override
    public Builder toBuilder() {
      return this == DEFAULT_INSTANCE
          ? new Builder() : new Builder().mergeFrom(this);
    }

    @Override
    protected Builder newBuilderForType(
        BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
    }
    /**
     * Protobuf type {@code DeviceInfo}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:DeviceInfo)
        cn.bupt.edu.entity.DeviceInfoProto.DeviceInfoOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return cn.bupt.edu.entity.DeviceInfoProto.internal_static_DeviceInfo_descriptor;
      }

      @Override
      protected FieldAccessorTable
          internalGetFieldAccessorTable() {
        return cn.bupt.edu.entity.DeviceInfoProto.internal_static_DeviceInfo_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                cn.bupt.edu.entity.DeviceInfoProto.DeviceInfo.class, cn.bupt.edu.entity.DeviceInfoProto.DeviceInfo.Builder.class);
      }

      // Construct using cn.bupt.edu.entity.DeviceInfoProto.DeviceInfo.newBuilder()
      private Builder() {
        maybeForceBuilderInitialization();
      }

      private Builder(
          BuilderParent parent) {
        super(parent);
        maybeForceBuilderInitialization();
      }
      private void maybeForceBuilderInitialization() {
        if (com.google.protobuf.GeneratedMessageV3
                .alwaysUseFieldBuilders) {
        }
      }
      @Override
      public Builder clear() {
        super.clear();
        id_ = "";

        name_ = "";

        temperature_ = 0;

        return this;
      }

      @Override
      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return cn.bupt.edu.entity.DeviceInfoProto.internal_static_DeviceInfo_descriptor;
      }

      @Override
      public cn.bupt.edu.entity.DeviceInfoProto.DeviceInfo getDefaultInstanceForType() {
        return cn.bupt.edu.entity.DeviceInfoProto.DeviceInfo.getDefaultInstance();
      }

      @Override
      public cn.bupt.edu.entity.DeviceInfoProto.DeviceInfo build() {
        cn.bupt.edu.entity.DeviceInfoProto.DeviceInfo result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      @Override
      public cn.bupt.edu.entity.DeviceInfoProto.DeviceInfo buildPartial() {
        cn.bupt.edu.entity.DeviceInfoProto.DeviceInfo result = new cn.bupt.edu.entity.DeviceInfoProto.DeviceInfo(this);
        result.id_ = id_;
        result.name_ = name_;
        result.temperature_ = temperature_;
        onBuilt();
        return result;
      }

      @Override
      public Builder clone() {
        return super.clone();
      }
      @Override
      public Builder setField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          Object value) {
        return super.setField(field, value);
      }
      @Override
      public Builder clearField(
          com.google.protobuf.Descriptors.FieldDescriptor field) {
        return super.clearField(field);
      }
      @Override
      public Builder clearOneof(
          com.google.protobuf.Descriptors.OneofDescriptor oneof) {
        return super.clearOneof(oneof);
      }
      @Override
      public Builder setRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          int index, Object value) {
        return super.setRepeatedField(field, index, value);
      }
      @Override
      public Builder addRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          Object value) {
        return super.addRepeatedField(field, value);
      }
      @Override
      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof cn.bupt.edu.entity.DeviceInfoProto.DeviceInfo) {
          return mergeFrom((cn.bupt.edu.entity.DeviceInfoProto.DeviceInfo)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(cn.bupt.edu.entity.DeviceInfoProto.DeviceInfo other) {
        if (other == cn.bupt.edu.entity.DeviceInfoProto.DeviceInfo.getDefaultInstance()) return this;
        if (!other.getId().isEmpty()) {
          id_ = other.id_;
          onChanged();
        }
        if (!other.getName().isEmpty()) {
          name_ = other.name_;
          onChanged();
        }
        if (other.getTemperature() != 0) {
          setTemperature(other.getTemperature());
        }
        this.mergeUnknownFields(other.unknownFields);
        onChanged();
        return this;
      }

      @Override
      public final boolean isInitialized() {
        return true;
      }

      @Override
      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        cn.bupt.edu.entity.DeviceInfoProto.DeviceInfo parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (cn.bupt.edu.entity.DeviceInfoProto.DeviceInfo) e.getUnfinishedMessage();
          throw e.unwrapIOException();
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }

      private Object id_ = "";
      /**
       * <code>string id = 1;</code>
       * @return The id.
       */
      public String getId() {
        Object ref = id_;
        if (!(ref instanceof String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          String s = bs.toStringUtf8();
          id_ = s;
          return s;
        } else {
          return (String) ref;
        }
      }
      /**
       * <code>string id = 1;</code>
       * @return The bytes for id.
       */
      public com.google.protobuf.ByteString
          getIdBytes() {
        Object ref = id_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b =
              com.google.protobuf.ByteString.copyFromUtf8(
                  (String) ref);
          id_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>string id = 1;</code>
       * @param value The id to set.
       * @return This builder for chaining.
       */
      public Builder setId(
          String value) {
        if (value == null) {
    throw new NullPointerException();
  }

        id_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>string id = 1;</code>
       * @return This builder for chaining.
       */
      public Builder clearId() {

        id_ = getDefaultInstance().getId();
        onChanged();
        return this;
      }
      /**
       * <code>string id = 1;</code>
       * @param value The bytes for id to set.
       * @return This builder for chaining.
       */
      public Builder setIdBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);

        id_ = value;
        onChanged();
        return this;
      }

      private Object name_ = "";
      /**
       * <code>string name = 2;</code>
       * @return The name.
       */
      public String getName() {
        Object ref = name_;
        if (!(ref instanceof String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          String s = bs.toStringUtf8();
          name_ = s;
          return s;
        } else {
          return (String) ref;
        }
      }
      /**
       * <code>string name = 2;</code>
       * @return The bytes for name.
       */
      public com.google.protobuf.ByteString
          getNameBytes() {
        Object ref = name_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b =
              com.google.protobuf.ByteString.copyFromUtf8(
                  (String) ref);
          name_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>string name = 2;</code>
       * @param value The name to set.
       * @return This builder for chaining.
       */
      public Builder setName(
          String value) {
        if (value == null) {
    throw new NullPointerException();
  }

        name_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>string name = 2;</code>
       * @return This builder for chaining.
       */
      public Builder clearName() {

        name_ = getDefaultInstance().getName();
        onChanged();
        return this;
      }
      /**
       * <code>string name = 2;</code>
       * @param value The bytes for name to set.
       * @return This builder for chaining.
       */
      public Builder setNameBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);

        name_ = value;
        onChanged();
        return this;
      }

      private int temperature_ ;
      /**
       * <code>int32 temperature = 3;</code>
       * @return The temperature.
       */
      public int getTemperature() {
        return temperature_;
      }
      /**
       * <code>int32 temperature = 3;</code>
       * @param value The temperature to set.
       * @return This builder for chaining.
       */
      public Builder setTemperature(int value) {

        temperature_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>int32 temperature = 3;</code>
       * @return This builder for chaining.
       */
      public Builder clearTemperature() {

        temperature_ = 0;
        onChanged();
        return this;
      }
      @Override
      public final Builder setUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return super.setUnknownFields(unknownFields);
      }

      @Override
      public final Builder mergeUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return super.mergeUnknownFields(unknownFields);
      }


      // @@protoc_insertion_point(builder_scope:DeviceInfo)
    }

    // @@protoc_insertion_point(class_scope:DeviceInfo)
    private static final cn.bupt.edu.entity.DeviceInfoProto.DeviceInfo DEFAULT_INSTANCE;
    static {
      DEFAULT_INSTANCE = new cn.bupt.edu.entity.DeviceInfoProto.DeviceInfo();
    }

    public static cn.bupt.edu.entity.DeviceInfoProto.DeviceInfo getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<DeviceInfo>
        PARSER = new com.google.protobuf.AbstractParser<DeviceInfo>() {
      @Override
      public DeviceInfo parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new DeviceInfo(input, extensionRegistry);
      }
    };

    public static com.google.protobuf.Parser<DeviceInfo> parser() {
      return PARSER;
    }

    @Override
    public com.google.protobuf.Parser<DeviceInfo> getParserForType() {
      return PARSER;
    }

    @Override
    public cn.bupt.edu.entity.DeviceInfoProto.DeviceInfo getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }

  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_DeviceInfo_descriptor;
  private static final
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_DeviceInfo_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    String[] descriptorData = {
      "\n\020DeviceInfo.proto\";\n\nDeviceInfo\022\n\n\002id\030\001" +
      " \001(\t\022\014\n\004name\030\002 \001(\t\022\023\n\013temperature\030\003 \001(\005B" +
      "(\n\022cn.bupt.edu.entityB\017DeviceInfoProto\210\001" +
      "\001b\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        });
    internal_static_DeviceInfo_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_DeviceInfo_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_DeviceInfo_descriptor,
        new String[] { "Id", "Name", "Temperature", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
