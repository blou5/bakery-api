package org.example;

import liquibase.changelog.DatabaseChangeLog;
import liquibase.changelog.ChangeLogParameters;
import liquibase.parser.ChangeLogParserFactory;
import liquibase.resource.ClassLoaderResourceAccessor;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LiquibaseChangelogParseTest {

    @Test
    void masterChangelogParses() throws Exception {
        DatabaseChangeLog changelog = ChangeLogParserFactory.getInstance()
                .getParser("db/changelog/db.changelog-master.yml", new ClassLoaderResourceAccessor())
                .parse("db/changelog/db.changelog-master.yml", new ChangeLogParameters(), new ClassLoaderResourceAccessor());

        assertThat(changelog.getChangeSets()).isNotEmpty();
    }
}
