package team.asd.handler;

import org.apache.ibatis.type.EnumTypeHandler;
import org.apache.ibatis.type.JdbcType;
import team.asd.constant.ArchivePriceType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ArchivePriceTypeHandler extends EnumTypeHandler<ArchivePriceType> {
	public ArchivePriceTypeHandler() {
		super(ArchivePriceType.class);
	}

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, ArchivePriceType parameter, JdbcType jdbcType) throws SQLException {
		ps.setString(i, parameter.getValue());
	}

	@Override
	public ArchivePriceType getNullableResult(ResultSet rs, String columnName) throws SQLException {
		return ArchivePriceType.getByString(rs.getString(columnName));
	}

	@Override
	public ArchivePriceType getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		return ArchivePriceType.getByString(rs.getString(columnIndex));
	}

	@Override
	public ArchivePriceType getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		return ArchivePriceType.getByString(cs.getString(columnIndex));
	}
}
