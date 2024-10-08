package io.github.rlshep.bjcp2015beerstyles.helpers.activity;

import io.github.rlshep.bjcp2015beerstyles.CategoryBodyActivity;
import io.github.rlshep.bjcp2015beerstyles.db.BjcpDataHelper;
import io.github.rlshep.bjcp2015beerstyles.domain.Section;

public class CategoryBodyHelper {
    private CategoryBodyActivity activity;
    private String categoryId;
    private static final String DELIM = ", ";
    private VitalStatisticsHelper vitalsHelper;

    public CategoryBodyHelper(CategoryBodyActivity activity, String categoryId) {
        this.activity = activity;
        this.categoryId = categoryId;
        this.vitalsHelper = new VitalStatisticsHelper(activity, categoryId);

    }

    public String getMainText() {
        StringBuilder text = new StringBuilder();

        text.append(getSectionsBody());
        text.append(vitalsHelper.getMainVitalStatistics());

        return text.toString();
    }

    private String getSectionsBody() {
        String body = "";
        for (Section section : BjcpDataHelper.getInstance(activity).getCategorySections(categoryId)) {
            body += section.getBody();
        }

        return body;
    }
}
