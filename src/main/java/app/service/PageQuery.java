package app.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.util.MultiValueMap;

public class PageQuery {

	private int page = 1;
	private int size = 10;
	private String q;
	private String sort;
	private Boolean asc;

	public PageQuery() {
	}

	public PageQuery(int page, int size) {
		this.page = page;
		this.size = size;
	}

	public PageQuery(MultiValueMap<String, String> params) {
		setPage(getInt(params, "page", page));
		setSize(getInt(params, "size", size));
		setQ(params.getFirst("q"));
		setSort(params.getFirst("sort"));
		setAsc(getBoolean(params, "asc"));
	}

	protected static int getInt(MultiValueMap<String, String> params, String name, int defaultValue) {
		String value = params.getFirst(name);
		if (value != null) {
			try {
				return Integer.parseInt(value);
			} catch (NumberFormatException e) {
				// ignore
			}
		}
		return defaultValue;
	}

	protected static Boolean getBoolean(MultiValueMap<String, String> params, String name) {
		String value = params.getFirst(name);
		return value != null ? Boolean.valueOf(value) : null;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public String getQ() {
		return q;
	}

	public void setQ(String q) {
		this.q = StringUtils.trimToNull(q);
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = StringUtils.lowerCase(StringUtils.trimToNull(sort));
	}

	public Boolean isAsc() {
		return asc;
	}

	public void setAsc(Boolean asc) {
		this.asc = asc;
	}

	public Pageable toPageable() {
		return new PageRequest(getPage() - 1, getSize());
	}

	public Pageable toPageable(String sort) {
		return new PageRequest(getPage() - 1, getSize(), new Sort(isAsc() ? Direction.ASC : Direction.DESC, sort));
	}

}
