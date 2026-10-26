Checkpoint

Write these yourself and paste the SQL plus results:

Users with no experience at all.
The most common skill and how many people have it.
People who share at least one skill with Alice (not Alice herself).
In your own words: why does profile_skills need the extra index on skill_id?
Each experience showing the page’s name when linked, and company_name otherwise (use COALESCE).
In your own words: why does experiences.organization_id use SET NULL while experiences.user_id uses CASCADE?
Each user’s location as “City, Country”, including users with no location or no profile.

Bonus: answer the twist in join lesson 6. Why do Dina and Eve still appear when the filter moves to WHERE?