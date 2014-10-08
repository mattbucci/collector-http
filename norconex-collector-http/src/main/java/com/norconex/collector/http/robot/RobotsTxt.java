/* Copyright 2010-2014 Norconex Inc.
 * 
 * This file is part of Norconex HTTP Collector.
 * 
 * Norconex HTTP Collector is free software: you can redistribute it and/or 
 * modify it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * Norconex HTTP Collector is distributed in the hope that it will be useful, 
 * but WITHOUT ANY WARRANTY; without even the implied warranty of 
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with Norconex HTTP Collector. If not, 
 * see <http://www.gnu.org/licenses/>.
 */
package com.norconex.collector.http.robot;

import java.util.Arrays;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.norconex.collector.core.filter.IReferenceFilter;

public class RobotsTxt {

    public static final float UNSPECIFIED_CRAWL_DELAY = -1;
    
    private final IReferenceFilter[] filters;
    private final float crawlDelay;
    private final String[] sitemapLocations;
    
    public RobotsTxt(IReferenceFilter[] filters) {
        this(filters, UNSPECIFIED_CRAWL_DELAY);
    }
    public RobotsTxt(IReferenceFilter[] filters, float crawlDelay) {
        this(filters, null, crawlDelay);
    }
    public RobotsTxt(IReferenceFilter[] filters, String[] sitemapLocations) {
        this(filters, sitemapLocations, UNSPECIFIED_CRAWL_DELAY);
    }
    public RobotsTxt(IReferenceFilter[] filters, String[] sitemapLocations, 
            float crawlDelay) {
        super();
        this.filters = ArrayUtils.clone(filters);
        this.sitemapLocations = ArrayUtils.clone(sitemapLocations);
        this.crawlDelay = crawlDelay;
    }

    public IReferenceFilter[] getFilters() {
        return ArrayUtils.clone(filters);
    }
    public String[] getSitemapLocations() {
        return ArrayUtils.clone(sitemapLocations);
    }
    public float getCrawlDelay() {
        return crawlDelay;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Float.floatToIntBits(crawlDelay);
        result = prime * result + Arrays.hashCode(filters);
        result = prime * result + Arrays.hashCode(sitemapLocations);
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        RobotsTxt other = (RobotsTxt) obj;
        if (Float.floatToIntBits(crawlDelay) != Float
                .floatToIntBits(other.crawlDelay)) {
            return false;
        }
        if (!Arrays.equals(filters, other.filters)) {
            return false;
        }
        if (!Arrays.equals(sitemapLocations, other.sitemapLocations)) {
            return false;
        }
        return true;
    }
    @Override
    public String toString() {
        ToStringBuilder builder = new ToStringBuilder(
                this, ToStringStyle.SHORT_PREFIX_STYLE);
        builder.append("filters", filters);
        builder.append("crawlDelay", crawlDelay);
        builder.append("sitemapLocations", sitemapLocations);
        return builder.toString();
    }
    
    
}
